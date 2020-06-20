package com.gang.etl.engine.container;

import com.gang.common.lib.utils.ReflectionUtils;
import com.gang.etl.engine.api.common.IComsumerService;
import com.gang.etl.engine.api.common.IProduceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.FactoryBeanRegistrySupport;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotatedBeanDefinitionReader;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Classname ProduceContainer
 * @Description TODO
 * @Date 2020/6/20 14:19
 * @Created by zengzg
 */
@Component
public class SyncContainer extends BaseContainer {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String BEAN_SUPPORT = "org.springframework.beans.factory.support.FactoryBeanRegistrySupport";
    private static final String BEAN_REGISTRY = "org.springframework.beans.factory.support.DefaultListableBeanFactory";

    //    @Autowired
    private AnnotatedBeanDefinitionReader registry;

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private ReflectionUtils reflectionUtils;

    /**
     * Data Producer Map
     */
    private static Map<String, Object> producerMap = new ConcurrentHashMap();

    /**
     * Data Comsumer Map
     */
    private static Map<String, Object> consumerMap = new ConcurrentHashMap();

    /**
     * Data Comsumer Map
     */
    private static Map<String, Object> beanDefinitionMap = new ConcurrentHashMap();

    /**
     * Bean Definitaion Map
     */
    private static Map<String, String> registryMap = new ConcurrentHashMap<>();

    @PostConstruct
    public void initContainer() {
        logger.info("------> Container Init : add producerMap and consumerMap Map <-------");
        getAllBeanDefinition();
        getBeanRegistry();
        createProduceMap();
        createConsumerMap();
    }

    /**
     * Cache ALL BeanDefinition
     */
    public void getAllBeanDefinition() {
        String[] beans = applicationContext.getBeanDefinitionNames();
        Arrays.asList(beans).forEach(item -> {
            Object bean = applicationContext.getBean(item);
            beanDefinitionMap.put(item, bean);
        });
        logger.info("------> init getAllBeanDefinition over <-------");
    }

    /**
     * Create producerMap Map By BeanFactory
     */
    public void createProduceMap() {
        String[] beans = applicationContext.getBeanNamesForType(IProduceService.class);
        Arrays.asList(beans).forEach(item -> {
            applicationContext.getBean(item);
            Object bean = applicationContext.getBean(item);
            producerMap.put(bean.getClass().getName(), bean);
        });
    }

    /**
     * Create Cousumer Map By BeanFactory
     */
    public void createConsumerMap() {
        String[] beans = applicationContext.getBeanNamesForType(IComsumerService.class);
        Arrays.asList(beans).forEach(item -> {
            applicationContext.getBean(item);
            Object bean = applicationContext.getBean(item);
            producerMap.put(bean.getClass().getName(), bean);
        });
    }

    /**
     *
     */
    public void getBeanRegistry() {
        //        FactoryBeanRegistrySupport support = reflectionUtils.springClassLoad(BEAN_SUPPORT);
    }



    public Map<String, Object> getProducerMap() {
        return producerMap;
    }

    public void setProducerMap(Map<String, Object> producerMap) {
        SyncContainer.producerMap = producerMap;
    }

    public Map<String, Object> getConsumerMap() {
        return consumerMap;
    }

    public void setConsumerMap(Map<String, Object> consumerMap) {
        SyncContainer.consumerMap = consumerMap;
    }
}
