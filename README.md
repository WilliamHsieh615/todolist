
![banner](https://github.com/WilliamHsieh615/todolist/blob/main/demo/banner.png?raw=true)

# TodoList

🔗 [專案連結](https://williamhsieh615.github.io/todolist/)

Sweetaste 是一個甜點購物網站，結合現代化前端技術與 Java 後端架構，提供完整的商品瀏覽、訂閱、購物車與結帳流程、訂單管理、會員資料維護，並整合多種第三方服務提升使用體驗。

---

## 專案介紹

---

## 技術棧與套件

### 前端技術

- 

### 後端技術

- 

## 使用說明

### 本地開發環境設定

1. 
2. 
3. 
4. **資料庫設定**
- 建立資料庫名稱：todolist。
- 匯入專案提供的資料表結構。
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

