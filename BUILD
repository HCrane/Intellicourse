java_binary(
  name = "createdb",
  srcs = ["DatabaseConstruction.java"],
  main_class = "DatabaseConstruction",
  deps = [":hibernate_lib", ":classes",],
  data = [
      "hibernate.cfg.xml"
 	 ],
  )

java_binary(
  name = "ta",
  srcs = ["test_area.java"],
  main_class = "test_area",
  deps = [":hibernate_lib", ":classes",  ],
  data = [
    "hibernate.cfg.xml"
    ],
  )

java_library(
  name = "controller",
  srcs = glob(["controller/*.java"]),
  deps = [":classes",
    ],
  )

java_library(
  name = "classes",
  srcs = glob(["classes/*.java"]),
  deps = [":hibernate_lib",
    ],
  )

java_import(
  name = "hibernate_lib",
  jars = [
    "libs/antlr-2.7.7.jar",
    "libs/dom4j-1.6.1.jar",
    "libs/hibernate-commons-annotations-4.0.5.Final.jar",
    "libs/hibernate-core-4.3.11.Final.jar",
    "libs/hibernate-jpa-2.1-api-1.0.0.Final.jar",
    "libs/jandex-1.1.0.Final.jar",
    "libs/javassist-3.18.1-GA.jar",
    "libs/jboss-logging-3.1.3.GA.jar",
    "libs/jboss-logging-annotations-1.2.0.Beta1.jar",
    "libs/jboss-transaction-api_1.2_spec-1.0.0.Final.jar",
    "libs/mysql-connector-java-5.1.37-bin.jar",
    "libs/prompt.jar",
    ],
  )
