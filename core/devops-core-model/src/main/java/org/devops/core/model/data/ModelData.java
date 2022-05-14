package org.devops.core.model.data;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

import org.devops.core.model.core.ModelHelper;
import org.devops.core.model.dto.ModelDataWhere;
import org.devops.core.model.dto.ModelWhere;
import org.devops.core.model.emun.ModelCondition;
import org.devops.core.model.emun.ModelOperator;
import org.devops.core.utils.exception.CommonRuntimeException;
import org.devops.core.utils.modules.future.factory.FutureFactory;
import org.devops.core.utils.util.FastJsonUtils;
import org.devops.core.utils.util.ListUtil;
import org.springframework.util.CollectionUtils;

import com.google.common.collect.Lists;
import com.google.common.util.concurrent.Futures;

import lombok.Data;

public class ModelData<T, K> {
	
	private static Map<String,Method> mapMethodCache = new HashMap<>();
	
	private static Map<String,Object> mapValueOfCache = new HashMap<>();
	
	private List<T> data;
	
	private List<OrderByVO> liOrderByVO = new ArrayList<>();
	
	private LimitVO limitVO;
	
	private ModelDataWhere<T> modelWhere = new ModelDataWhere<T>();
	
	private Long listTotalCount = 0L;
	
	public ModelData() {
		
	}
	
	public ModelData(List<T> data) {
		this.data = data;
	}
	
	public void setData(List<T> data) {
		this.data = data;
		this.clear();
	}
	
	private void clear() {
		if(liOrderByVO != null) {
			liOrderByVO.clear();
		}
		limitVO = null;
		if(modelWhere != null) {
			modelWhere.clear();
		}
	}
	
	public T get() {
		if(CollectionUtils.isEmpty(data)) {
			return null;
		}
		List<T> list = list();
		if(CollectionUtils.isEmpty(list)) {
			return null;
		}
		return list.get(0);
	}
	
	public List<T> list() {
		if(CollectionUtils.isEmpty(data)) {
			return new ArrayList<>();
		}
		try{
			List<T> result = new ArrayList<>(data);
			result = beginFilter(result);
			listTotalCount = (long)result.size();
			result = beginSort(result);
			result = beginLimit(result);
			return result;
		}catch(CommonRuntimeException e) {
			throw e;
		}finally {
			clear();
		}
	}
	
	public long getListTotalCount() {
		return listTotalCount;
	}
	
	public long count() {
		if(CollectionUtils.isEmpty(data)) {
			return 0;
		}
		try{
			List<T> result = data;
			result = beginFilter(result);
			return result.size();
		}catch(CommonRuntimeException e) {
			throw e;
		}finally {
			clear();
		}
	}
	
	@SuppressWarnings("unchecked")
	private <T2> Map<T2,T> toMap(List<T> list,String field,Class<T2> cla)
    {
        Map<T2,T> result = new HashMap<T2,T>();
        if(list == null || list.isEmpty()){
            return result;
        }
        for(T o : list){
        	if( o == null) continue;
            try{
                Object value = ModelHelper.getValue(o, field);
                
                if(cla.equals(String.class)){
                	if(value != null){
                		result.put((T2)value.toString(), o);
	                }
                }else{
	                Method valueOf = cla.getMethod("valueOf", String.class);
	                if(value == null){
	                	result.put((T2)valueOf.invoke(null, "0"), o);
	                }else{
	                	result.put((T2)valueOf.invoke(null, value.toString()), o);
	                }
                }
                
            }catch(Exception e){
                
            }
        }
        return result;
    }
	
	public Map<Long,T> map(String column){
		List<T> list = list();
		return toMap(list,column,Long.class);
	}
	
	public Map<String,T> stringMap(String column){
		List<T> list = list();
		return toMap(list,column,String.class);
	}
	
	public List<String> listString(String field) {
		return listSingle(field,String.class);
	}
	
	public List<Long> listLong(String field) {
		return listSingle(field,Long.class);
	}
	
	public String getString(String field) {
		return getSingle(field,String.class);
	}
	
	public Long getLong(String field) {
		return getSingle(field,Long.class);
	}
	
	public <T2> List<T2> listSingle(String field, Class<T2> clazz){
		List<T> list = list();
		if (ListUtil.isNull(list)) {
		  list = new ArrayList<>();
    }
		List<T2> result = new ArrayList<T2>();
		for(T o : list){
			result.add(ModelHelper.convertSingle(ModelHelper.getValue(o, field), clazz));
		}
		return result;
	}
	
	public <T2> T2 getSingle(String field,Class<T2> clazz){
		this.limit(1);
		List<T2> list = listSingle(field, clazz);
		if(CollectionUtils.isEmpty(list)){
			return null;
		}
		return list.get(0);
	}
	
	public ModelData<T, K> orderBy(String...args) {
		if(args.length == 0) {
			return this;
		}
		
		String key = null;
		for(String arg : args){
			if(key == null){
				key = arg;
				continue;
			}
			OrderByVO orderByVO = new OrderByVO();
			orderByVO.setColumn(key);
			orderByVO.setType(arg.trim());
			liOrderByVO.add(orderByVO);
			key = null;
		}
		if(key != null){
			OrderByVO orderByVO = new OrderByVO();

			String[] keys = key.split(" ");
			if(keys.length == 2) {
				orderByVO.setColumn(keys[0].trim());
				orderByVO.setType(keys[1].trim().toUpperCase());
			} else {
				orderByVO.setColumn(key);
				orderByVO.setType("ASC");
			}
			
			liOrderByVO.add(orderByVO);
		}
		return this;
	}
	
	public ModelData<T, K> limit(int size) {
		limitVO = new LimitVO();
		limitVO.setSize(size);
		return this;
	}
	
	public ModelData<T, K> limit(int start, int size) {
		limitVO = new LimitVO();
		limitVO.setSize(size);
		limitVO.setStart(start);
		return this;
	}
	
	public ModelData<T, K> where(String column,Object value){
		return this.where(column, value, null, ModelCondition.AND);
	}
	
	public ModelData<T, K> where(String column,Object value,ModelOperator mo){
		return this.where(column, value, mo, ModelCondition.AND);
	}
	
	public ModelData<T, K> where(String column,Object value,ModelCondition mc){
		return this.where(column, value, null, mc);
	}
	
	public ModelData<T, K> where(String column,Object value,ModelOperator mo,ModelCondition mc){
		modelWhere.add(column, value, mo, mc);
		return this;
	}
	
	
	public ModelData<T, K> where(ModelWhere mw){
		if(mw == null || !mw.hasWhere()){
			return this;
		}
		modelWhere.add(new ModelDataWhere<T>(mw));
		return this;
	}
	
	public ModelData<T, K> add(ModelWhere mw,ModelCondition mc){
		if(mw == null || !mw.hasWhere()){
			return this;
		}
		modelWhere.add(new ModelDataWhere<T>(mw), mc);
		return this;
	}
	
	interface ModelDataPartitionHandler<T> {
		void partition(List<T> list);
	}
	
	protected void partition(List<T> list,ModelDataPartitionHandler<T> modelDataPartitionHandler) {
		
		int pageSize = 2000;
		
		List<List<T>> lists = Lists.partition(list,pageSize);
		
		if(lists.size() <= 1) {
			modelDataPartitionHandler.partition(list);
		} else {
			ThreadPoolExecutor threadPoolExecutor = FutureFactory.newThreadPoolExecutor(lists.size());
			
			Future<Void> future = null;
			List<Future<Void>> liFuture = new ArrayList<>(); 
			
			for(List<T> li : lists) {
				future = threadPoolExecutor.submit(new Callable<Void>() {
					@Override
					public Void call() throws Exception {
						modelDataPartitionHandler.partition(li);
						return null;
					}
				});
				liFuture.add(future);
			}
			for(Future<Void> f : liFuture) {
				Futures.getUnchecked(f);
			}
		}
	}
	
	
	@SuppressWarnings("rawtypes")
	private void beginFilterOne(T o, ModelDataWhere<T> modelWhere) {
		Object v = ModelHelper.getValue(o, modelWhere.getColumn());
		if(v == null) {
			return;
		}
		Object localValue = modelWhere.getValue();
		Set<String> setValue = modelWhere.getSetValue();
		try {
			if(modelWhere.getModelOperator().equals(ModelOperator.EQ)) {
				if(compareTo(v,localValue) == 0) {
					modelWhere.addResult(o);
				}
			} if(modelWhere.getModelOperator().equals(ModelOperator.NEQ)) {
				if(compareTo(v,localValue) != 0) {
					modelWhere.addResult(o);
				}
			} else if(modelWhere.getModelOperator().equals(ModelOperator.GT)) {
				if(compareTo(v,localValue) > 0) {
					modelWhere.addResult(o);
				}
			} else if(modelWhere.getModelOperator().equals(ModelOperator.EGT)) {
				if(compareTo(v,localValue) >= 0) {
					modelWhere.addResult(o);
				}
			} else if(modelWhere.getModelOperator().equals(ModelOperator.LT)) {
				if(compareTo(v,localValue) < 0) {
					modelWhere.addResult(o);
				}
			} else if(modelWhere.getModelOperator().equals(ModelOperator.ELT)) {
				if(compareTo(v,localValue) <= 0) {
					modelWhere.addResult(o);
				}
			} else if(modelWhere.getModelOperator().equals(ModelOperator.LIKE)) {
				String strV = v.toString();
				String strLocal = localValue.toString();
				if(strV.indexOf(strLocal) >= 0) {
					modelWhere.addResult(o);
				}
			} else if(modelWhere.getModelOperator().equals(ModelOperator.LIKE_LEFT)) {
				String strV = v.toString();
				String strLocal = localValue.toString();
				if(strV.startsWith(strLocal)) {
					modelWhere.addResult(o);
				}
			} else if(modelWhere.getModelOperator().equals(ModelOperator.LIKE_RIGHT)) {
				String strV = v.toString();
				String strLocal = localValue.toString();
				if(strV.endsWith(strLocal)) {
					modelWhere.addResult(o);
				}
			} else if(modelWhere.getModelOperator().equals(ModelOperator.NOT_IN)) {
				if(setValue != null) {
					if(!setValue.contains(v.toString())) {
						modelWhere.addResult(o);
					}
				} else if(localValue instanceof List) {
					for(Object ov : (List)localValue) {
						if(compareTo(v, ov) != 0) {
							modelWhere.addResult(o);
						}
					}
				} 
			} else if(modelWhere.getModelOperator().equals(ModelOperator.IN)) {
				if(setValue != null) {
					if(setValue.contains(v.toString())) {
						modelWhere.addResult(o);
					}
				} else if(localValue instanceof List) {
					for(Object ov : (List)localValue) {
						if(compareTo(v, ov) == 0) {
							modelWhere.addResult(o);
						}
					}
				} 
			}
		} catch (Exception e) {
		} 
		return;
	}
	
	private void beginFilter(T o,List<ModelDataWhere<T>> liModelWhere) {
		
		for(ModelDataWhere<T> modelWhere : liModelWhere) {
			if(!CollectionUtils.isEmpty(modelWhere.getSubWhere())) {
				beginFilter(o, modelWhere.getSubWhere());
			} else {
				beginFilterOne(o, modelWhere);
			}
		}
	}
	
	private List<T> beginGetResult(List<ModelDataWhere<T>> liModelWhere) {
		List<T> result = null;
		
		List<T> r = null;
		for(ModelDataWhere<T> modelWhere : liModelWhere) {
			if(!CollectionUtils.isEmpty(modelWhere.getSubWhere())) {
				r = beginGetResult(modelWhere.getSubWhere());
			} else {
				r = modelWhere.getResult();
			}
			if(result == null) {
				result = new ArrayList<>();
				result.addAll(r);
			} else {
				if(modelWhere.getModelCondition().equals(ModelCondition.AND)) {
					if(CollectionUtils.isEmpty(r)) {
						result.clear();
					} else {
						// 交集
						try {
							Set<T> setResult = new HashSet<>();
							for(T o : result) {
								setResult.add(o);
							}
							result.clear();
							result = new ArrayList<>();
							for(T o : r) {
								if(setResult.contains(o)) {
									result.add(o);
								}
							}
						}catch (Exception e) {
							// 如果没有ID，那么使用list的retainAll方法，这个方法有性能问题，数据量一大就会很慢
							result.retainAll(r);
						}
					}
				} else {
					if(!CollectionUtils.isEmpty(r)) {
						// 并集
						result.removeAll(r);
						result.addAll(r);
					}
				}
			}
			r.clear();
		}
		return result;
	}
	
	private List<T> beginFilter(List<T> list) {
		if(!modelWhere.hasWhere()) {
			return list;
		}
		partition(list,new ModelDataPartitionHandler<T>() {
			@Override
			public void partition(List<T> list) {
				for(T o : list) {
					beginFilter(o, modelWhere.getSubWhere());
				}
			}
		});
		
		List<T> result = beginGetResult(modelWhere.getSubWhere());
		if(result == null) {
			result = new ArrayList<>();
		}
		return result;
	}
	
	private int compareTo(Object o1,Object o2) {
		if(o1 == null || o2 == null) {
			throw new CommonRuntimeException("值不能被比较");
		}
		Class<?> clazz = o1.getClass();
		if(clazz.equals(Date.class)) {
			Long o1Time = ((Date)o1).getTime() / 1000;
			Long o2Time = ((Date)o2).getTime() / 1000;
			return o1Time.compareTo(o2Time);
		} else if(!clazz.equals(String.class) && !clazz.equals(o2.getClass()) ) {
			
			String keyValueOf = clazz.getName() + "." + "valueOf" + "." + o2.toString();
			Object value = mapValueOfCache.get(keyValueOf);
			if(value == null) {
				try {
					String key = clazz.getName() + "." + "valueOf"; 
					Method method = mapMethodCache.get(key);
					if(method == null) {
						method = clazz.getDeclaredMethod("valueOf", String.class);
						mapMethodCache.put(key, method);
					}
					o2 = method.invoke(null, o2.toString());
					mapValueOfCache.put(keyValueOf, o2);
				} catch (Exception e) {
					throw new CommonRuntimeException("值不能被比较");
				} 
			} else {
				o2 = value;
			}
			
		}
		try {
			String key = o1.getClass().getName() + "." + "compareTo"; 
			Method method = mapMethodCache.get(key);
			if(method == null) {
				method = clazz.getDeclaredMethod("compareTo", o1.getClass());
				mapMethodCache.put(key, method);
			}
			return (int)method.invoke(o1, o2);
			
		}catch(Exception e) {
			throw new CommonRuntimeException("值不能被比较");
		}
	}
	
	
	private int compareTo(T o1, T o2,OrderByVO orderByVO) {
		try {
			Object o1Value = ModelHelper.getValue(o1, orderByVO.getColumn());
			Object o2Value = ModelHelper.getValue(o2, orderByVO.getColumn());
			if(o1Value == null && o2Value == null) {
				return 0;
			} else if(o1Value == null) {
				return -1;
			} else if(o2Value == null) {
				return 1;
			}
			String key = o1Value.getClass().getName() + "." + "compareTo"; 
			Method method = mapMethodCache.get(key);
			if(method == null) {
				method = o1Value.getClass().getDeclaredMethod("compareTo", o1Value.getClass());
				mapMethodCache.put(key, method);
			}
			int r = (int)method.invoke(o1Value, o2Value);
			
			if(orderByVO.getType().equalsIgnoreCase("DESC")) {
				if(r < 0) {
					r = 1;
				}else if(r > 0) {
					r = -1;
				}
			}
			return r;
		} catch(Exception e) {
			throw new CommonRuntimeException(e.getCause());
		}
	}
	
	private List<T> beginSort(int index, OrderByVO preOrderByVO, List<T> list) {
		if(index > liOrderByVO.size() - 1) {
			return list;
		}
		OrderByVO orderByVO = liOrderByVO.get(index);
		if(orderByVO == null) {
			return list;
		}
		list.sort(new Comparator<T>() {
			@Override
			public int compare(T o1, T o2) {
				if(index > 0 && compareTo(o1,o2,preOrderByVO) != 0) {
					return 0;
				}
				return compareTo(o1, o2, orderByVO);
			}
		});
	
		return beginSort(index + 1, orderByVO, list);
	}
	private List<T> beginSort(List<T> list) {
		if(liOrderByVO.size() == 0) {
			return list;
		}
		return beginSort(0, null, list);
	}
	
	private List<T> beginLimit(List<T> list) {
		if(limitVO == null) {
			return list;
		}
		List<T> result = new ArrayList<T>(0);
        
        if(list == null){
            return result;
        }
        int last = limitVO.getStart() + limitVO.getSize();
        if(list.size() < last){
            last = list.size();
        }
        for(int i= limitVO.getStart(); i<last;i++){
            result.add(list.get(i));
        }
        return result;
	}
	
	@Data
	private class OrderByVO {
		private String column;
		private String type;
	}
	
	@Data
	private class LimitVO {
		private Integer start = 0;
		private Integer size;
	}
	
	@Override
	public String toString() {
		if(CollectionUtils.isEmpty(data)) {
			return "[]";
		}
		return FastJsonUtils.toJsonString(data);
	}
}
