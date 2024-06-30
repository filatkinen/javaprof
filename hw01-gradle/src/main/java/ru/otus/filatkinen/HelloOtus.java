package ru.otus.filatkinen;

import com.google.common.collect.Ordering;
import com.google.common.io.Resources;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.util.*;
import java.util.jar.Attributes;
import java.util.jar.Manifest;


public class HelloOtus {
    public static void main(String[] args) throws IOException {
        final int listCount = 10;
        final int maxInt = 1000;

        List<Integer> list = new ArrayList<>();

        Random r = new Random();

        for (int i = 1; i <= listCount; i++) {
            list.add(r.nextInt(maxInt));
        }

        Ordering<Integer> ordering = Ordering.natural();

        System.out.println("Testing guava methods with gradle.");
        System.out.printf("Generating List<Integer> with size %d\n", list.size());
        System.out.printf("Every elemenent List<Integer> from 0 to %d\n", maxInt);

        System.out.println("Using guava methods  max and min:");
        System.out.printf("Max integer: %d\n", ordering.max(list));
        System.out.printf("Min integer: %d\n", ordering.min(list));

        System.out.println("\nUsed guava version:");
        System.out.println(determineGuavaManifestVersion());
        System.out.println(determineGuavaMavenVersion());
    }

//Taken from https://stackoverflow.com/questions/40893964/detect-which-guava-version-is-used
    public static String determineGuavaManifestVersion() throws IOException {
        Enumeration<URL> resources = Resources.class.getClassLoader().getResources("META-INF/MANIFEST.MF");
        while (resources.hasMoreElements()) {
            Manifest manifest;
            try (InputStream inputStream = resources.nextElement().openStream()) {
                manifest = new Manifest(inputStream);
            }
            Attributes mainAttributes = manifest.getMainAttributes();
            String symbolicName = mainAttributes.getValue("Bundle-SymbolicName");
            if ("com.google.guava".equals(symbolicName)) {
                return mainAttributes.getValue("Bundle-Version");
            }
        }
        return null;
    }

    public static String determineGuavaMavenVersion() throws IOException {
        String resourceName = "META-INF/maven/com.google.guava/guava/pom.properties";
        Properties properties = new Properties();
        try (InputStream inputStream = Resources.getResource(resourceName).openStream()) {
            properties.load(inputStream);
        }
        return properties.getProperty("version");
    }
}