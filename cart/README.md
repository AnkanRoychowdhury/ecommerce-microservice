# Get Started Developing
EcomCartManagementSystem Microservice is intended to develop & maintain the cart management facility for an ecommerce. 

![CartService Architecture](https://res.cloudinary.com/dedidawqd/image/upload/v1734895814/cartservice_dlb1za.png)
## Let's try it out

#### Before you start
- Install Java [At least version 11]
- Clone the repo: 
    ```git clone https://github.com/AnkanRoychowdhury/EcomCartManagementSystem.git ```
- Set Environment Variables on Your OS
  - On Linux/MacOS:
      ```bash
        export DATABASE_URL=<DATABASE_URL>
        export DATABASE_USERNAME=<DATABASE_USERNAME>
        export DATABASE_PASSWORD=<DATABASE_PASSWORD>
        export REDIS_HOST=<REDIS_HOST>
        export REDIS_PORT=<REDIS_PORT>
        export REDIS_USERNAME=<REDIS_USERNAME>
        export REDIS_PASSWORD=<REDIS_PASSWORD>
      ```
      To make the above variables persistent, you can add them to your shell configuration file (e.g., ~/.bashrc or ~/.zshrc):
      <br><br>Example:
      ```bash
      echo "export DATABASE_URL=jdbc:mysql://localhost/cartdb_dev" >> ~/.bashrc
      source ~/.bashrc
      ```
  - On Windows:
    * Open the Start Menu and search for **"Environment Variables"**
    * Click **Edit the system environment variables**.
    * Under **System Properties**, click the Environment Variables button.
    * In the **System variables** section, click New and add:
       * `DATABASE_URL=jdbc:mysql://localhost/cartdb_dev`
       * `DATABASE_USERNAME=root`
       * `DATABASE_PASSWORD=password`
    * Click OK to save the changes.
<br><br>
- Run the Application: 
    ```bash 
    ./mvnw spring-boot:run
    ```

#### Important endpoints
- http://localhost:8282 - Server URL | _By default port is: `8282` though you can change it._
- http://localhost:8282/swagger-ui/index.html - Swagger Documentation
- https://ecomcartjavadoc.netlify.app - Cart Service Java Documentation to look upon package, class hierarchy details

### API Endpoints
Contains general input logic and validation: carts/cart items, guest cart manage api contracts.

| Method	 | Path	                                        | Description	                                                | User authentication	 | Available from UI |
|---------|----------------------------------------------|-------------------------------------------------------------|:--------------------:|:-----------------:|
| GET	    | /api/v1/carts?cartId={cartId}	               | Get specified cart data	                                    |          ✓           |         ✓         |
| POST	   | /api/v1/carts	                               | Create a cart with the given data in payload	               |          ×           |         ✓         |
| PUT	    | /api/v1/carts?cartId={cartId}                | Update the cart with the given data in payload	             |          ×           |        	✓         |
| PATCH	  | /api/v1/carts/items?={cartId}	               | Update or add cart items of a specified cartId	             |          ×           |         ✓         |
| DELETE	 | /api/v1/carts?cartId={cartId}                | Soft Delete a cart                                          |          ✓           |         ×         |
| POST	   | /api/v1/carts/merge/{userId}?cartId={cartId} | Merge guest cart to the logged in user                      |          ✓           |         ✓         |
| GET	    | /api/v1/carts/guest?cartId={cartId}          | Get guest user cart data from redis by the specified cartId |          ×           |         ✓         |

### Detailed API Reference

1. #### Create cart 

    ```shell
      POST /api/v1/carts
    ```
   | Parameter   | Type     | Description                    | Required |
   |:------------|:---------|:-------------------------------|:--------:|
   | `userId`    | `string` | User ID (If logged in)         |    ×     |
   | `cartItems` | `Array`  | To be added products into cart |    ✓     |

    * Create cart with user
      * Request Body:
          ```json
           {
             "userId": "1234",
             "cartItems": [
                 { "productId": "prod-1", "quantity": 2, "price": 13.75 },
                 { "productId": "prod-2", "quantity": 1, "price": 19.78 }
             ]
           }
          ```
      * Response:
          ```json
           {
               "status": "OK",
               "message": "Cart created successfully",
               "data": {
                   "userId": "1234",
                   "cartItems": [
                       {
                           "productId": "prod-1",
                           "quantity": 2,
                           "price": 13.75
                       },
                       {
                           "productId": "prod-2",
                           "quantity": 1,
                           "price": 19.78
                       }
                   ],
                   "createdAt": "2024-11-23T22:07:03.768+00:00",
                   "updatedAt": "2024-11-23T22:07:03.768+00:00"
               },
               "errors": null
            }
          ```
    * Create cart without user (guest cart)
      * Request Body:
         ```json
           {
             "cartItems": [
                 { "productId": "prod-1", "quantity": 2, "price": 13.75 },
                 { "productId": "prod-2", "quantity": 1, "price": 19.78 }
             ]
           }
         ```
      * Response:
          ```json
           {
               "status": "OK",
               "message": "Cart created successfully",
               "data": {
                   "userId": "",
                   "cartItems": [
                       {
                           "productId": "prod-1",
                           "quantity": 2,
                           "price": 13.75
                       },
                       {
                           "productId": "prod-2",
                           "quantity": 1,
                           "price": 19.78
                       }
                   ],
                   "createdAt": "2024-11-23T22:07:03.768+00:00",
                   "updatedAt": "2024-11-23T22:07:03.768+00:00"
               },
               "errors": null
            }
          ```

2. #### Get specified cart data

    ```shell
      GET /api/v1/carts?cartId={cartId}
    ```
   | Parameter   | Type     | Description           | Required |
   |:------------|:---------|:----------------------|:--------:|
   | `cartId`    | `string` | Cart ID               |    ✓     |

   * Response:
     ```json
      {
        "status": "OK",
        "message": "Cart retrieved successfully",
        "data": {
            "userId": "user12",
            "cartItems": [
                 {
                      "productId": "prod001",
                      "quantity": 2,
                      "price": 23.79
                 },
                 {
                      "productId": "prod002",
                      "quantity": 1,
                      "price": 19.65
                 }
            ],
            "createdAt": "2024-11-23T21:04:23.000+00:00",
            "updatedAt": "2024-11-23T21:04:23.000+00:00"
        },
        "errors": null
      }
     ```
3. #### Get guest user cart data from redis by the specified cartId

    ```shell
      GET /api/v1/carts/guest?cartId={cartId}
    ```
   | Parameter   | Type     | Description           | Required |
   |:------------|:---------|:----------------------|:--------:|
   | `cartId`    | `string` | Cart ID               |    ✓     |

### Maven Parent overrides

Due to Maven's design, elements are inherited from the parent POM to the project POM.
While most of the inheritance is fine, it also inherits unwanted elements like `<license>` and `<developers>` from the
parent.
To prevent this, the project POM contains empty overrides for these elements.
If you manually switch to a different parent and actually want the inheritance, you need to remove those overrides.