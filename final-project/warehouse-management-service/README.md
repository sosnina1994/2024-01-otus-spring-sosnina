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
* Services
    * **[ToolTypeService](src/main/java/ru/otus/hw/services/ToolTypeService.java)** - интерфейс сервиса управления
      типами инструментов
    * **[ToolTypeServiceImp](src/main/java/ru/otus/hw/services/ToolTypeServiceImp.java)** - реализация интерфейса
      сервиса управления типами инструментов
    * **[ToolBrandService](src/main/java/ru/otus/hw/services/ToolBrandService.java)** - интерфейс сервиса управления
      брендами (производителями) инструментов
    * **[ToolBrandServiceImpl](src/main/java/ru/otus/hw/services/ToolBrandServiceImpl.java)** - реализация интерфейса
      сервиса управления брендами (производителями) инструментов
    * **[ToolService](src/main/java/ru/otus/hw/services/ToolService.java)** - интерфейс сервиса управления
      инструментами
    * **[ToolServiceImpl](src/main/java/ru/otus/hw/services/ToolServiceImpl.java)** - реализация интерфейса
      сервиса управления инструментами
    * **[ToolBalanceService](src/main/java/ru/otus/hw/services/ToolBalanceService.java)** - интерфейс сервиса управления
      балансом инструментов
    * **[ToolBalanceServiceImpl](src/main/java/ru/otus/hw/services/ToolBalanceServiceImpl.java)** - реализация
      интерфейса сервиса управления балансом инструментов
    * **[ToolArrivalService](src/main/java/ru/otus/hw/services/ToolArrivalService.java)** - интерфейс сервиса управления
      поступлением инструментов
    * **[ToolArrivalServiceImpl](src/main/java/ru/otus/hw/services/ToolArrivalServiceImpl.java)** - реализация
      интерфейса сервиса управления поступлением инструментов
    * **[ToolArrivalActService](src/main/java/ru/otus/hw/services/ToolArrivalActService.java)** - интерфейс сервиса
      управления актами поступления инструментов
    * **[ToolArrivalActServiceImpl](src/main/java/ru/otus/hw/services/ToolArrivalActServiceImpl.java)** - реализация
      интерфейса сервиса управления актами поступления инструментов
    * **[ToolIssueService](src/main/java/ru/otus/hw/services/ToolIssueService.java)** - интерфейс сервиса управления
      списанием инструментов
    * **[ToolIssueServiceImpl](src/main/java/ru/otus/hw/services/ToolIssueServiceImpl.java)** - реализация
      интерфейса сервиса управления списанием инструментов
    * **[ToolArrivalActService](src/main/java/ru/otus/hw/services/ToolIssueActService.java)** - интерфейс сервиса
      управления актами списания инструментов
    * **[ToolIssueActServiceImpl](src/main/java/ru/otus/hw/services/ToolIssueActServiceImpl.java)** - реализация
      интерфейса сервиса управления актами списания инструментов
* Repositories
    * **[ToolTypeRepository](src/main/java/ru/otus/hw/repositories/ToolTypeRepository.java)** - JPA-репозиторий типов
      инструментов
    * **[ToolBrandRepository](src/main/java/ru/otus/hw/repositories/ToolBrandRepository.java)** - JPA-репозиторий
      брендов (производителей) инструментов
    * **[ToolRepository](src/main/java/ru/otus/hw/repositories/ToolRepository.java)** - JPA-репозиторий инструментов
    * **[ToolBalanceRepository](src/main/java/ru/otus/hw/repositories/ToolBalanceRepository.java)** - JPA-репозиторий
      баланса инструментов
    * **[ToolArrivalActRepository](src/main/java/ru/otus/hw/repositories/ToolArrivalActRepository.java)** -
      JPA-репозиторий актов поступления инструментов
    * **[ToolIssueActRepository](src/main/java/ru/otus/hw/repositories/ToolIssueActRepository.java)** -
      JPA-репозиторий актов списания инструментов
    * **[UserRepository](src/main/java/ru/otus/hw/repositories/UserRepository.java)** - JPA-репозиторий пользователей

