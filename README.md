
![banner](https://github.com/WilliamHsieh615/todolist/blob/main/demo/banner.png?raw=true)

# TodoList

🔗 [專案連結](https://williamhsieh615.github.io/todolist/)

---

## 專案介紹
TodoList 是一個前後端分離的待辦清單應用，前端使用 Vue + Vite，後端使用 Java Spring Boot 提供 REST API，包含會員註冊 / 登入（JWT）、Todo CRUD 等功能。專案範例適合用來練習前後端認證、CORS 與 JWT 的整合。

---

## 功能介紹
- 會員註冊 / 登入（JWT 驗證）/ 重設密碼
- Todo 新增 / 修改 / 刪除 / 標記完成
- 本地開發可使用 Vite dev server 與 Spring Boot backend 同時測試
- (Demo) 部署在 GitHub Pages（前端靜態）與後端本地或另行部署

---

## 技術棧與套件

### 前端技術

- Vue 3 + Vite（dev server、proxy 設定）
- Axios（與後端通訊，帶 withCredentials 處理 cookie）
- Vite plugin (eg. vite-plugin-vue-devtools) — 可參考 vite.config.js

### 後端技術

- Java（Spring Boot） + Spring Security（JWT 驗證與 Filter）
- JJWT（io.jsonwebtoken）處理 JWT
- 資料庫：MySQL（或相容的 RDBMS）

---

## 使用說明

### 前置需求

- Java 17/21（專案 log 顯示 Java 21，也可用你環境相容版本）
- Maven
- Node.js 18+ 與 npm / pnpm / yarn
- MySQL

1. ##資料庫（MySQL）##
(1) 建立資料庫名稱：todolist。
(2) 匯入專案提供的資料表結構。
    ```sql
    -- 建立 members 資料表
    CREATE TABLE members (
        member_id INT          NOT NULL PRIMARY KEY AUTO_INCREMENT,
        email     VARCHAR(256) NOT NULL UNIQUE KEY,
        password  VARCHAR(256) NOT NULL,
        nickname  VARCHAR(256) NOT NULL,
        birthday           DATE         NOT NULL,
        created_date       TIMESTAMP    NOT NULL,
        last_modified_date TIMESTAMP    NOT NULL
    );

    -- 建立 todos 資料表
    CREATE TABLE todos (
        todo_id INT AUTO_INCREMENT PRIMARY KEY,
        member_id INT NOT NULL,
        text VARCHAR(255) NOT NULL,
        completed BOOLEAN NOT NULL DEFAULT FALSE,
        created_date TIMESTAMP    NOT NULL,
        last_modified_date TIMESTAMP    NOT NULL,
        FOREIGN KEY (member_id) REFERENCES members(member_id)
    );
- 確認資料庫帳號密碼與權限設定。


    ```bash
      npm install --save-dev gh-pages // 只要做一次就好
      npm run build
      npm run deploy

