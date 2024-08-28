# SSWeb 后端

此仓库包含 SSWeb 项目的后端代码。后端使用 Java、Spring Boot 和 Maven 实现。

## 目录

- [项目概述](#项目概述)
- [快速开始](#快速开始)
- [构建和运行](#构建和运行)
- [使用](#使用)
- [贡献](#贡献)
- [许可证](#许可证)

## 项目概述

SSWeb 后端为 SSWeb 应用程序提供 RESTful API。它处理用户认证、数据处理和其他服务器端功能。

## 快速开始

### 前提条件

- Java 11 或更高版本
- Maven 3.6 或更高版本

### 克隆仓库

要克隆仓库，请运行以下命令：

```sh
git clone -b backend https://github.com/Owl23007/SSWeb.git
cd SSWeb
git checkout -b backend
```

## 构建和运行

### 构建项目

要构建项目，请导航到项目目录并运行：

application.java

### 运行应用程序

要运行应用程序，请使用以下命令：

```sh
mvn spring-boot:run
```

应用程序将启动在 `http://localhost:8080`。

## 使用

### API 端点

- **GET /api/users**: 获取用户列表。
- **POST /api/users**: 创建新用户。
- **GET /api/users/{id}**: 根据 ID 获取用户。
- **PUT /api/users/{id}**: 根据 ID 更新用户。
- **DELETE /api/users/{id}**: 根据 ID 删除用户。

### 示例请求

要创建新用户，请向 `/api/users` 发送包含以下 JSON 负载的 POST 请求：

```json
{
  "username": "exampleUser",
  "password": "examplePassword"
}
```

## 贡献

欢迎贡献！请 fork 仓库并创建 pull request 提交您的更改。

## 许可证

此项目根据 MIT 许可证授权。有关详细信息，请参阅 `LICENSE` 文件。
