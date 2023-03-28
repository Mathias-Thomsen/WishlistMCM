```mermaid
classDiagram
    note "From Model till Service"
    Model <|-- Entity
    Model <|-- Repository
    Entity <|-- Service
    class Controller{
        -int sizeInFeet
        -canEat()
    }
    class Model{
        +bool is_wild
        +run()
    }
    class Entity{
        +int age
        +String gender
        +isMammal()
        +mate()
        +bool is_wild
        +run()
    }
    class Repository{
        +bool is_wild
        +run()
    }
    class Service{
        +bool is_wild
        +run()
    }

