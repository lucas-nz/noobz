package com.zhousz.zerg.apect;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.stereotype.Component;

import com.alibaba.druid.support.json.JSONUtils;
import com.zhousz.zerg.annotation.Log;
import com.zhousz.zerg.dao.mysql.TLogsDao;
import com.zhousz.zerg.domain.TLogs;
import com.zhousz.zerg.util.HttpContextUtil;
import com.zhousz.zerg.util.IPUtil;

/**
 * @ClassName:  LogAspect   
 * @Description: 切面类, 切点为使用 @Log 注解标志的方法, 使用 @Around 环绕通知.   
 * @author: zhousz
 * @date:   2018年11月8日 下午6:16:29   
 *
 */
@Aspect
@Component
public class LogAspect {
	
	@Autowired
	private TLogsDao tLogsDao;
	
	@Pointcut("@annotation(com.zhousz.zerg.annotation.Log)")
	public void pointcut(){}
	
	@Around("pointcut()")
	public Object around(ProceedingJoinPoint point) {
		Object result = null;
		// 开始时间
		long beginTime = System.currentTimeMillis();
		try {
			// 执行方法
			result = point.proceed();
		} catch (Throwable e) {
			e.printStackTrace();
		}
		// 执行时间
		long time = System.currentTimeMillis() - beginTime;
		// 保存日志
		saveLog(point, time);
		return result;
	}

	private void saveLog(ProceedingJoinPoint point, long time) {
		MethodSignature signature = (MethodSignature)point.getSignature();
		Method method = signature.getMethod();
		TLogs log = new TLogs();
		Log logAnnotation = method.getAnnotation(Log.class);
		if (null != logAnnotation) {
			// 注解上的描述
			log.setAction(logAnnotation.value());
		}
		// 请求的方法名
		String className = point.getTarget().getClass().getName();
		String methodName = signature.getName();
		Object[] args = point.getArgs();
		LocalVariableTableParameterNameDiscoverer u = new LocalVariableTableParameterNameDiscoverer();
		String[] parameterNames = u.getParameterNames(method);
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		if (null != args && null != parameterNames) {
			for (int i = 0; i < args.length; i++) {
				parameterMap.put(parameterNames[i], args[i]);
			}
		}
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("calssName", className);
		data.put("methodName", methodName);
		data.put("parameter", parameterMap);
		String dataString = JSONUtils.toJSONString(data);
		String ip = IPUtil.getIpAddr(HttpContextUtil.getHttpServletRequest());
		log.setData(dataString);
		log.setIp(ip);
		log.setCreated(time);
		tLogsDao.save(log);
	}
	
}
