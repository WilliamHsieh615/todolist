<script setup>
const props = defineProps(['todos'])
const emit = defineEmits(['remove', 'toggle'])
</script>

<template>
  <li v-for="todo in props.todos.filter(t => !t.completed)" :key="todo.id">
    <label class="todoList_label">
      <input class="todoList_input" type="checkbox" :checked="todo.completed" @change="emit('toggle', todo.id)" />
      <span>{{ todo.text }}</span>
    </label>
    <a href="#" @click.prevent="emit('remove', todo.id)">
      <i class="fa-solid fa-trash"></i>
    </a>
  </li>
</template>