package com.xjd.test.bean;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public abstract class BeanGuard {

	protected static ConcurrentHashMap<Class, Set<FieldGuard>> guardMap = new ConcurrentHashMap<Class, Set<FieldGuard>>();

	public static void guard(List<Object> beanList, String... guardGroups) {
		if (beanList != null) {
			for (Object bean : beanList) {
				guard(bean, guardGroups);
			}
		}
	}

	public static void guard(Object bean, String... guardGroups) {
		if (bean == null) return;

		Class clazz = bean.getClass();
		if (clazz.isArray() || clazz.isInterface() || clazz.isEnum()) return;

		guard(clazz, bean, guardGroups);
	}

	protected static void resolveClass(Class clazz) {
		Field[] fields = clazz.getDeclaredFields();
		Set<FieldGuard> tmpSet = new HashSet<BeanGuard.FieldGuard>(fields.length);
		for (Field f : fields) {
			GuardGroup guardGroup = f.getAnnotation(GuardGroup.class);
			if (guardGroup == null) {
				continue;
			}
			String[] guardGroupVals = guardGroup.value();
			if (guardGroupVals != null && guardGroupVals.length > 0) {
				Set<String> gs = new HashSet<String>(guardGroupVals.length);
				for (String val : guardGroupVals) {
					if (val == null || val.trim().equals("")) {
						continue;
					}
					gs.add(val.trim());
				}
				if (gs.size() > 0) {
					FieldGuard fg = new FieldGuard();
					fg.setGuard(gs);
					f.setAccessible(true);
					fg.setField(f);
					tmpSet.add(fg);
				}
			}
		}

		Set<FieldGuard> set = new HashSet<BeanGuard.FieldGuard>(tmpSet.size());
		set.addAll(tmpSet);
		guardMap.put(clazz, set);

		if (clazz.getSuperclass() != null) {
			resolveClass(clazz.getSuperclass());
		}
	}

	protected static void guard(Class clazz, Object bean, String... guardGroups) {
		Set<FieldGuard> set = guardMap.get(clazz);
		if (set == null) {
			resolveClass(clazz);
			set = guardMap.get(clazz);
		}

		boolean basic = false;
		if (guardGroups == null || guardGroups.length == 0) {
			basic = true;
		}
		for (FieldGuard fg : set) {
			if (!basic) {
				if (containsAny(fg.getGuard(), guardGroups)) {
					continue;
				}
			}
			try {
				fg.getField().set(bean, null);
			} catch (Exception e) {
				// do-nothing
			}
		}
		if (clazz.getSuperclass() != null) {
			guard(clazz.getSuperclass(), bean, guardGroups);
		}
	}

	protected static boolean containsAny(Set<String> set, String... ss) {
		for (String s : ss) {
			if (set.contains(s)) {
				return true;
			}
		}
		return false;
	}

	public static class FieldGuard {
		protected Set<String> guard;
		protected Field field;

		public Set<String> getGuard() {
			return guard;
		}

		public void setGuard(Set<String> guard) {
			this.guard = guard;
		}

		public Field getField() {
			return field;
		}

		public void setField(Field field) {
			this.field = field;
		}

		@Override
		public int hashCode() {
			return field == null ? 0 : field.hashCode();
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj) return true;
			if (obj == null) return false;
			if (!(obj instanceof FieldGuard)) return false;
			FieldGuard fg = (FieldGuard) obj;
			if (this.field == fg.field) return true;
			if (this.field == null) return false;
			return this.field.equals(fg.field);
		}

	}
}
