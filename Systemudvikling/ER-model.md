```mermaid
erDiagram
    CUSTOMER ||--o{ ORDER : places
    ORDER ||--|{ LINE-ITEM : contains
    PRODUCT ||--o{ LINE-ITEM : includes
    CUSTOMER {
        string name
        string email
        string phone
    }
    ORDER {
        string orderNumber
        date orderDate
        enum orderStatus
    }
    PRODUCT {
        string productId
        string productName
        decimal price
    }
    LINE-ITEM {
        decimal quantity
        decimal price
    }
```
