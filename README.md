

=======
## Blog Application

### 概述
这是一个使用 Spring Boot 构建的博客应用程序。它提供了用户注册、登录和用户信息管理的功能。该应用程序使用 JWT 进行身份验证，并使用 MyBatis 进行数据库交互。

### 先决条件
- Java 21
- Maven
- MySQL

### 设置

#### 克隆仓库
```bash
git clone https://github.com/Owl23007/SSWeb.git
git checkout -b backend
```

#### 配置数据库
1. 创建一个 MySQL 数据库。
2. 更新 `application.properties` 文件中的数据库配置。

#### 构建项目
```bash
mvn clean install
```

#### 运行应用程序
```bash
mvn spring-boot:run
```

### 接口

#### 用户注册
- **URL:** `/user/register`
- **方法:** `POST`
- **参数:**
  - `username` (String, 必填)
  - `email` (String, 必填)
  - `password` (String, 必填)
- **描述:** 注册一个新用户。

#### 用户登录
- **URL:** `/user/login`
- **方法:** `POST`
- **参数:**
  - `username` (String, 必填)
  - `password` (String, 必填)
- **描述:** 用户登录并返回 JWT 令牌。

#### 获取用户信息
- **URL:** `/user/userinfo`
- **方法:** `GET`
- **描述:** 获取已登录用户的信息。

#### 更新用户信息
- **URL:** `/user/update`
- **方法:** `PUT`
- **参数:** 请求体中的 `User` 对象
- **描述:** 更新用户信息。

#### 更新头像
- **URL:** `/user/updateAvatar`
- **方法:** `PATCH`
- **参数:**
  - `url` (String, 必填)
- **描述:** 更新用户头像。

#### 更新密码
- **URL:** `/user/updatePwd`
- **方法:** `PATCH`
- **参数:** 包含 `old\_pwd`、`new\_pwd` 和 `re\_pwd` 的 JSON 对象
- **描述:** 更新用户密码。

#### 注销账号
- **URL:** `/user/deleteAcc`
- **方法:** `DELETE`
- **参数:**
  - `password` (String, 必填)
- **描述:** 注销用户账号。

### 使用的技术
- **Spring Boot**: 构建 Java 应用程序的框架。
- **MyBatis**: SQL 映射框架。
- **JWT**: 用于身份验证的 JSON Web 令牌。
- **Lombok**: 减少样板代码的 Java 库。
- **Hibernate Validator**: 用于验证用户输入。

### 许可证
此项目使用 MIT 许可证。
>>>>>>> 6039d42a59b2fcf53d7b64779585cd6a277e41ca
