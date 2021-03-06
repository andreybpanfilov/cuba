/*
 * Copyright (c) 2008-2016 Haulmont.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package com.haulmont.cuba.core.sys;

import org.springframework.orm.jpa.vendor.EclipseLinkJpaDialect;
import org.springframework.orm.jpa.vendor.EclipseLinkJpaVendorAdapter;

import java.util.Map;

public class CubaEclipseLinkJpaVendorAdapter extends EclipseLinkJpaVendorAdapter {

    protected final EclipseLinkJpaDialect jpaDialect;

    public CubaEclipseLinkJpaVendorAdapter() {
        jpaDialect = new CubaEclipseLinkJpaDialect();
        jpaDialect.setLazyDatabaseTransaction(true);
    }

    @Override
    public Map<String, Object> getJpaPropertyMap() {
        Map<String, Object> map = super.getJpaPropertyMap();
        for (String name : AppContext.getPropertyNames()) {
            if (name.startsWith("eclipselink.")) {
                map.put(name, AppContext.getProperty(name));
            }
        }
        return map;
    }

    @Override
    public EclipseLinkJpaDialect getJpaDialect() {
        return jpaDialect;
    }
}