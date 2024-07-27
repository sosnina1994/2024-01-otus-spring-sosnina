# Проектная работа.

# Тема: "MES: прототип системы управления складом"

## Классы

* Root
    * **[WarehouseManagementServiceApplication](src/main/java/ru/otus/hw/WarehouseManagementServiceApplication.java)** -
      основной класс приложения
* Configs
    * **[SecurityConfig](src/main/java/ru/otus/hw/configs/SecurityConfig.java)** - конфигурация авторизации
    * **[SwaggerConfig](src/main/java/ru/otus/hw/configs/SwaggerConfig.java)** - конфигурация SwaggerOpenApi
* Controllers
    * **[ExceptionController](src/main/java/ru/otus/hw/controllers/ExceptionController.java)** - контроллер обработки
      ошибок
    * **[ToolTypeController](src/main/java/ru/otus/hw/controllers/ToolTypeController.java)** - контроллер управления
      типами инструментов
    * **[ToolBrandController](src/main/java/ru/otus/hw/controllers/ToolBrandController.java)** - контроллер управления
      брендами (производителями) инструментов
    * **[ToolController](src/main/java/ru/otus/hw/controllers/ToolController.java)** - контроллер управления
      инструментами
    * **[ToolBalanceController](src/main/java/ru/otus/hw/controllers/ToolBalanceController.java)** - контроллер учёта
      баланса инструментов
    * **[ToolArrivalController](src/main/java/ru/otus/hw/controllers/ToolArrivalController.java)** - контролер учёта
      поступления инструментов
    * **[ToolArrivalActController](src/main/java/ru/otus/hw/controllers/ToolArrivalActController.java)** - контроллер
      отчетов поступления инструмента
    * **[ToolIssueController](src/main/java/ru/otus/hw/controllers/ToolIssueController.java)** - контроллер
      выдачи инструментов
    * **[ToolIssueActController](src/main/java/ru/otus/hw/controllers/ToolIssueActController.java)** - контроллер
      отчетов выдачи инструментов

