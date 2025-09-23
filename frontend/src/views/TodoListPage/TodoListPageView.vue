<script setup>

import TheNav from '@/components/layout/TheNav.vue';
import { ref, computed, onMounted } from 'vue'
import Swal from 'sweetalert2';
import { addTodo, removeTodo, toggleTodo, getTodos } from '@/plugins/todos';

const todos = ref([])
const newTodo = ref('')

// 新增
const renderAddTodo = async () => {
  if (!newTodo.value.trim()) {
    Swal.fire({
      icon: "warning",
      title: "尚未輸入代辦事項",
      text: "請輸入待辦事項",
      confirmButtonColor: "#d33",
    });
    return
  } 
  try {
    const result = await addTodo(newTodo.value)
    todos.value.push({
      id: result.data.todoId,
      text: result.data.text,
      completed: result.data.completed
    })
    newTodo.value = ''
  } catch (error) {
    console.error('新增待辦失敗', error)
  }
}

// 刪除
const renderRemoveTodo = async (id) => {
  console.log(id);
  try {
    await removeTodo(id)
    todos.value = todos.value.filter(todo => todo.id !== id)
  } catch (error) {
    console.error('刪除待辦失敗', error)
  }
}

// 已完成切換
const renderToggleTodo = async (id) => {
  const todo = todos.value.find(t => t.id === id)
  if (!todo) return
  try {
    await toggleTodo(id)
    todo.completed = !todo.completed
  } catch (error) {
    console.error('更新狀態失敗', error)
  }
}

// 已完成數量
const completedCount = computed(() => todos.value.filter(t => t.completed).length)


onMounted(async () => {

  // 取得 todos
  try {
    const result = await getTodos()

    console.log(result);

    todos.value = result.data.data.map(todo => ({
      id: todo.itemId,
      text: todo.text,
      completed: todo.completed
    }));
  } catch (error) {

    let message = ''
    if (error.response && error.response.status === 400) {
      message = error.response.data.message;
    } else {
      message = "請稍後再試！！";
    }
    console.error("取得待辦事項失敗", message);

    Swal.fire({
      icon: "warning",
      title: "取得待辦事項失敗",
      text: message,
      confirmButtonColor: "#d33",
    });
  }
})

</script>

<template>

  <div class="wrap bg-half">

    <div id="todoListPage" class="todoListPage">

      <TheNav />

      <div class="todoList_Content">
        <div class="inputBox">
          <input v-model="newTodo" type="text" placeholder="請輸入待辦事項">
          <a href="#" @click.prevent="renderAddTodo"><i class="fa fa-plus"></i></a>
        </div>
        <div class="todoList_list" v-if="todos.length > 0">
          <ul class="todoList_tab">
            <li>
              <router-link to="/todolist-page/all" class="tab-link" active-class="active" exact>
                全部
              </router-link>
            </li>
            <li>
              <router-link to="/todolist-page/active" class="tab-link" active-class="active">
                待完成
              </router-link>
            </li>
            <li>
              <router-link to="/todolist-page/completed" class="tab-link" active-class="active">
                已完成
              </router-link>
            </li>
          </ul>
          <div class="todoList_items">
            <ul class="todoList_item">
              <router-view v-slot="{ Component }">
                <component :is="Component" :todos="todos" @remove="renderRemoveTodo" @toggle="renderToggleTodo" />
              </router-view>
            </ul>
            <div class="todoList_statistics">
              <p> {{ completedCount }} 個已完成項目</p>
            </div>
          </div>
        </div>
        <div class="noItem" v-else>
          <p>目前尚無待辦事項</p>
          <img src="/todo_banner.png" alt="todo_banner">
        </div>
      </div>

    </div>
  </div>

</template>

<style scoped lang="scss">
.wrap {
  height: 100vh;
  background-image: linear-gradient(175deg, #FFD370 60%, #fff 40%);
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

        a.active {
          color: $font-color;
          border-bottom: 2px solid $font-color;
        }
      }

      ::v-deep(.todoList_items) {
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

        .todoList_input:checked~span {
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
          color: red;
          opacity: 0;
        }

        li a i {
          font-size: 20px;
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

    .noItem {
      p {
        font-size: 28px;
        text-align: center;
        margin: 30px;
      }

      img {
        padding: 20px;
      }
    }
  }
}
</style>
