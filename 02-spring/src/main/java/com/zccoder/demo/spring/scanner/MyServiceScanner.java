package com.zccoder.demo.spring.scanner;

import com.zccoder.demo.spring.scanner.service.MyService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.FileSystemResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternUtils;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * MyService注解扫描
 *
 * @author zc
 * @date 2021/01/04
 */
public class MyServiceScanner {

    private static Logger logger = LoggerFactory.getLogger(MyServiceScanner.class);

    /**
     * Mapper扫描包路径
     */
    private final String[] basePackages = {"com.zccoder.demo.spring.scanner.service"};

    private ResourceLoader resourceLoader = new FileSystemResourceLoader();
    private ResourcePatternResolver resolver = ResourcePatternUtils.getResourcePatternResolver(resourceLoader);
    private MetadataReaderFactory metadataReaderFactory = new CachingMetadataReaderFactory(resourceLoader);

    /**
     * 被@MyService注解修饰的类名（全路径）
     */
    private List<String> allService = new ArrayList<>(16);

    public static void main(String[] args) throws Exception {
        new MyServiceScanner().run();
    }

    private void run() throws IOException {
        // 遍历包，依次进行校验
        for (String basePackage : this.basePackages) {
            this.scanByPackage(basePackage);
        }

        // 如果列表为空，则结束方法
        if (this.allService.isEmpty()) {
            return;
        }

        for (String service : allService) {
            logger.info("被@MyService修饰：{}", service);
        }
    }

    private void scanByPackage(String basePackage) throws IOException {
        Set<Class<?>> mapperSet = this.doScan(basePackage);
        if (CollectionUtils.isEmpty(mapperSet)) {
            return;
        }

        this.allService.addAll(mapperSet.stream().map(Class::getName).collect(Collectors.toList()));
    }

    private Set<Class<?>> doScan(String scanPath) throws IOException {
        Set<Class<?>> mapperClassSet = new HashSet<>(64);
        String packageSearchPath = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX
                .concat(scanPath.replace('.', '/'))
                .concat("/**/*.class");

        Resource[] resources = this.resolver.getResources(packageSearchPath);
        for (Resource resource : resources) {
            if (resource.isReadable()) {
                MetadataReader metadataReader = this.metadataReaderFactory.getMetadataReader(resource);
                try {
                    if (metadataReader.getClassMetadata().isInterface()) {
                        // 获取类类实例
                        Class<?> serviceClass = Class.forName(metadataReader.getClassMetadata().getClassName());
                        // 判断类上是否有@MyService注解
                        MyService annotation = serviceClass.getAnnotation(MyService.class);
                        if (Objects.nonNull(annotation)) {
                            // 仅接口时添加
                            mapperClassSet.add(serviceClass);
                        }
                    }
                } catch (Exception e) {
                    // 忽略
                }
            }
        }
        return mapperClassSet;
    }

}
