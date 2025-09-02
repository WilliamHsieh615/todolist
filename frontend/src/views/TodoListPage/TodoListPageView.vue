<script setup>

import TheNav from '@/components/layout/TheNav.vue';

</script>

<template>

  <div class="wrap bg-half">

    <div id="todoListPage" class="todoListPage">

      <TheNav/>

      <div class="todoList_Content">
        <div class="inputBox">
          <input type="text" placeholder="請輸入待辦事項">
          <a href="#">
            <i class="fa fa-plus"></i>
          </a>
        </div>
        <div class="todoList_list">
          <ul class="todoList_tab">
            <li>
              <router-link to="/todolist-page" class="tab-link" active-class="active">全部</router-link>
            </li>
            <li>
              <router-link to="/todolist-page/active" class="tab-link" active-class="active">待完成</router-link>
            </li>
            <li>
              <router-link to="/todolist-page/completed" class="tab-link" active-class="active">已完成</router-link>
            </li>
          </ul>
          <div class="todoList_items">
            <ul class="todoList_item">
              <router-view/>
              <!-- <li>
                <label class="todoList_label">
                  <input class="todoList_input" type="checkbox" value="true">
                  <span>把冰箱發霉的檸檬拿去丟</span>
                </label>
                <a href="#">
                  <i class="fa fa-times"></i>
                </a>
              </li>
              <li>
                <label class="todoList_label">
                  <input class="todoList_input" type="checkbox" value="true">
                  <span>打電話叫媽媽匯款給我</span>
                </label>
                <a href="#">
                  <i class="fa fa-times"></i>
                </a>
              </li>
              <li>
                <label class="todoList_label">
                  <input class="todoList_input" type="checkbox" value="true">
                  <span>整理電腦資料夾</span>
                </label>
                <a href="#">
                  <i class="fa fa-times"></i>
                </a>
              </li>
              <li>
                <label class="todoList_label">
                  <input class="todoList_input" type="checkbox" value="true">
                  <span>繳電費水費瓦斯費</span>
                </label>
                <a href="#">
                  <i class="fa fa-times"></i>
                </a>
              </li>
              <li>
                <label class="todoList_label">
                  <input class="todoList_input" type="checkbox" value="true">
                  <span>約vicky禮拜三泡溫泉</span>
                </label>
                <a href="#">
                  <i class="fa fa-times"></i>
                </a>
              </li>
              <li>
                <label class="todoList_label">
                  <input class="todoList_input" type="checkbox" value="true">
                  <span>約ada禮拜四吃晚餐</span>
                </label>
                <a href="#">
                  <i class="fa fa-times"></i>
                </a>
              </li> -->
            </ul>
            <div class="todoList_statistics">
              <p> 5 個已完成項目</p>
            </div>
          </div>
        </div>
      </div>

    </div>
  </div>

</template>

<style scoped lang="scss">
.wrap {
  background-image: linear-gradient(175deg,
      $background-color 40%,
      #FFFFFF 40%,
      #FFFFFF 80%,
      $background-color 80%);
}

@media (max-width: 500px) {
  .wrap {
    background-image: linear-gradient(175deg, $background-color 100%, #FFFFFF 0%);
  }
}

.todoListPage {
  display: flex;
  flex-direction: column;
  gap: 30px;
  padding: 50px;

  .todoList_Content {
    display: flex;
    flex-direction: column;
    gap: 20px;

    .inputBox {
      width: 100%;
      display: flex;
      align-items: center;
      position: relative;
      box-shadow: 0 0 15px 0 rgba(0, 0, 0, 0.15);

      input {
        background: #FFFFFF;
        border: none;
        border-radius: 10px;
        position: relative;
        width: 100%;
        height: 47px;
        font-size: 1rem;
        padding-left: 16px;
      }

      a {
        display: flex;
        justify-content: center;
        align-items: center;
        width: 40px;
        height: 40px;
        position: absolute;
        background: $font-color;
        color: #FFFFFF;
        font-size: 20px;
        text-decoration: none;
        text-align: center;
        border-radius: 10px;
        top: 4px;
        right: 4px;
        padding: 10px;

        &:hover {
          background: #000000;
        }
      }
    }

    .todoList_list {
      background: #FFFFFF;
      border-radius: 10px;
      box-shadow: 0 0 15px 0 rgba(0, 0, 0, 0.15);

      .todoList_tab {
        display: flex;
        justify-content: space-evenly;

        li {
          width: 100%;
        }

        a {
          display: block;
          color: #9F9A91;
          text-decoration: none;
          line-height: 20px;
          font-weight: bold;
          text-align: center;
          padding: 16px;
          border-bottom: 2px solid #efefef;
        }

        a:active {
          color: $font-color;
          border-bottom: 2px solid $font-color;
        }
      }

      .todoList_items {
        padding-top: 23px;
        padding-left: 24px;
        padding-right: 17px;
        padding-bottom: 32px;

        .todoList_item {
          margin-bottom: 8px;
        }

        .todoList_label {
          width: 100%;
          display: flex;
          align-items: center;
          border-bottom: 1px solid #e5e5e5;
          padding-bottom: 15px;
          color: $font-color;
          line-height: 20.27px;
        }

        .todoList_input {
          width: 20px;
          height: 20px;
          border: 1px solid #9F9A91;
          border-radius: 5px;
          margin-right: 16px;
        }

        .todoList_input:checked ~ span {
          color: #9F9A91;
          text-decoration: line-through;
          transition: all 0.4s ease-in-out;
        }

        li {
          display: flex;
          align-items: center;
          margin-bottom: 17px;
        }

        li a {
          margin-left: 17px;
          display: block;
          font-size: 14px;
          color: $font-color;
          opacity: 0;
        }

        li:hover a {
          opacity: 1;
        }
      }

      .todoList_statistics {
        display: flex;
        justify-content: space-between;

        p {
          color: $font-color;
          font-size: 0.875rem;
        }

        a {
          color: #9F9A91;
          font-size: 0.875rem;
          text-decoration: none;
        }
      }
    }
  }
}
</style>
