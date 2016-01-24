java_binary(
  name = "intellicourse",
  srcs = ["main.java"],
  main_class = "main",
  deps = [
    ":Viewer",
    ":classes",
    ":hibernate_lib",
    ":datepicker_lib"
    ],
    data = [
        "hibernate.cfg.xml"
    ],
)


java_binary(
  name = "createdb",
  srcs = ["DatabaseConstruction.java"],
  main_class = "DatabaseConstruction",
  deps = [":hibernate_lib", ":classes",":datepicker_lib"],
  data = [
      "hibernate.cfg.xml"
 	 ],
  )

java_binary(
  name = "ta",
  srcs = ["test_area.java"],
  main_class = "test_area",
  deps = [":hibernate_lib", ":classes", ":controller", ],
  data = [
    "hibernate.cfg.xml"
    ],
  )

java_library(
  name = "Viewer",
  srcs = glob(["viewer/*.java"]),
  deps = [":controller", ":classes", ":hibernate_lib", ":datepicker_lib"],
)

java_library(
  name = "controller",
  srcs = glob(["controller/*.java"]),
  deps = [":classes",
          ":hibernate_lib",
    ],
  )

java_library(
  name = "classes",
  srcs = glob(["classes/*.java"]),
  deps = [":hibernate_lib","datepicker_lib"
    ],
  )

java_import(
  name = "datepicker_lib",
  jars = [
    "libs/jdatepicker-1.3.4.jar"
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
