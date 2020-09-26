# My Money
This project is a simple fintech (e-wallet) system.

## Technology Stack
1. Java 1.8
2. Spring Framework
3. Spring Boot
4. Maven
5. Spring Tools Suite 4

## API Documentation
Module Name | Path | HTTP Method | Function |
--- | --- | --- | --- |
User | /api/rest/v1/register/ | POST | for register admin/merchant/customer/ |
Wallet | /api/rest/v1/wallet/create | POST | for create the wallet according user type (merchant/customer) |
Topup Transaction| /api/rest/v1/transaction/topup | POST | for increase user balance |
Payment Transaction | /api/rest/v1/transaction/payment | POST | for increase merchant balance and decrease customer balance |


## Request and Response Data
**Register Merchant**
*Request*
```
[
  {
    "email":"merchant@mymoney.com",
    "password":"12345",
    "verifyPassword":"12345",
    "fullName":"merchant mymoney",
    "phoneNumber":"081345567789",
    "role":"merchant"
  }
]
```
*Response*
```
{
    "id": "035a5d75-5c51-4e13-a70c-5bdfd3271929",
    "createdBy": "system",
    "updatedBy": null,
    "createdTime": "25-09-2020 23:00:20",
    "updatedTime": null,
    "email": "merchant@mymoney.com",
    "fullName": "merchant mymoney",
    "phoneNumber": "081345567789",
    "role": null
}
```

**Register Customer**
*Request*
```
[
  {
    "email":"customer@mymoney.com",
    "password":"12345",
    "verifyPassword":"12345",
    "fullName":"customer mymoney",
    "phoneNumber":"0813123234434",
    "role":"customer"
  }
]
```
*Response*
```
{
    "id": "9823178a-1979-430b-b389-abc65494a8b6",
    "createdBy": "system",
    "updatedBy": null,
    "createdTime": "25-09-2020 23:00:20",
    "updatedTime": null,
    "email": "customer@mymoney.com",
    "fullName": "customer mymoney",
    "phoneNumber": "0813123234434",
    "role": null
}
```

**Create Wallet Merchant/Customer** 
*Request*
```
[
  {
    "userId":"9823178a-1979-430b-b389-abc65494a8b6"
  }
]
```
*Response*
```
[
  {
     "id": "9d47383a-c880-4a81-8055-e93ab6711189",
     "createdBy": "system",
     "updatedBy": null,
     "createdTime": "25-09-2020 23:17:20",
     "updatedTime": null,
     "userId": null,
     "balance": 0
  }
]
```


**Topup Transaction Merchant/Customer**
*Request**
```
[
  {
     "referenceId":"dfdg-sdaq-hgf",
     "creditWallet":20000,
     "userId":"9823178a-1979-430b-b389-abc65494a8b6",
     "typeTransaction":"topup",
     "description":"First topup"
  }
]
```
*Response*
```
[
  {
      "id": "238efded-5e20-41f1-977d-687e70e7f6e3",
      "createdBy": "system",
      "updatedBy": null,
      "createdTime": "26-09-2020 08:56:46",
      "updatedTime": null,
      "referenceId": "dweg-sdaq-hgf",
      "user": {
          "id": "9823178a-1979-430b-b389-abc65494a8b6",
          "createdBy": "system",
          "updateBy": null,
          "createdTime": "2020-09-25T16:00:20.317+00:00",
          "updateTime": "2020-09-25T16:00:20.317+00:00",
          "status": "ACTIVE",
          "email": "customer@mymoney.com",
          "fullName": "customer mymoney",
          "phoneNumber": "0813123234434",
          "role": "ROLE_CUSTOMER"
      },
      "date": "2020-09-26T01:56:46.431+00:00",
      "description": "First topup",
      "type": "TOPUP",
      "topup": {
          "id": "8a95f6ee-ead0-4d52-99ed-0b03fc83401a",
          "createdBy": "system",
          "updateBy": null,
          "createdTime": "2020-09-26T01:56:46.428+00:00",
          "updateTime": "2020-09-26T01:56:46.428+00:00",
          "status": "ACTIVE",
          "creditWallet": 20000
      },
      "payment": null
  }
]
```

**Payment Transaction Customer to Merchant**
*Request*
```
[
  {
     "referenceId":"fbve-sdaq-hgf",
     "amount":1000,
     "customerId":"9823178a-1979-430b-b389-abc65494a8b6",
     "merchantId":"1237154a-1923-150s-b320-def65494a8b9",
     "typeTransaction":"Payment",
     "description":"First payment"
  }
]
```
*Response*
```
[
  {
      "id": "7f181aeb-54c7-44ec-94bd-e5560f24aac9",
      "createdBy": "system",
      "updatedBy": null,
      "createdTime": "25-09-2020 23:18:34",
      "updatedTime": null,
      "referenceId": "fbve-sdaq-hgf",
      "user": {
          "id": "9823178a-1979-430b-b389-abc65494a8b6",
          "createdBy": "system",
          "updateBy": null,
          "createdTime": "2020-09-25T16:00:20.317+00:00",
          "updateTime": "2020-09-25T16:00:20.317+00:00",
          "status": "ACTIVE",
          "email": "customer@mymoney.com",
          "fullName": "customer mymoney",
          "phoneNumber": "0813123234434",
          "role": "ROLE_CUSTOMER"
      },
      "date": "2020-09-25T16:18:34.858+00:00",
      "description": "First payment",
      "type": "PAYMENT",
      "topup": null,
      "payment": {
          "id": "540c2a04-644d-4879-a460-e79ab272a896",
          "createdBy": "system",
          "updateBy": null,
          "createdTime": "2020-09-25T16:18:34.858+00:00",
          "updateTime": "2020-09-25T16:18:34.858+00:00",
          "status": "ACTIVE",
          "creditWallet": 2000,
          "debitWallet": 2000
      }
  }
]
```

## How to use it ?
1. First clone this repository
2. Open your IDE(STS is recommended)
3. Wait a minutes because it will take a time
4. If it's finished then run this project and test it, have fun ^^
