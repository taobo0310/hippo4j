/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package cn.hippo4j.tools.logrecord.parse;

import cn.hippo4j.tools.logrecord.context.LogRecordContext;
import org.springframework.context.expression.MethodBasedEvaluationContext;
import org.springframework.core.ParameterNameDiscoverer;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * Log record evaluation context.
 *
 * @author chen.ma
 * @date 2021/10/24 21:25
 */
public class LogRecordEvaluationContext extends MethodBasedEvaluationContext {

    public LogRecordEvaluationContext(Object rootObject, Method method, Object[] arguments,
                                      ParameterNameDiscoverer parameterNameDiscoverer, Object ret, String errorMsg) {
        super(rootObject, method, arguments, parameterNameDiscoverer);

        Map<String, Object> variables = LogRecordContext.getVariables();
        if (variables != null && variables.size() > 0) {
            for (Map.Entry<String, Object> entry : variables.entrySet()) {
                setVariable(entry.getKey(), entry.getValue());
            }
        }

        setVariable("_ret", ret);
        setVariable("_errorMsg", errorMsg);
    }
}