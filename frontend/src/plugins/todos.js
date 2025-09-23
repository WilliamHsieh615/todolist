import api from './api'

// 新增 todo
export const addTodo = (text) => api.post('/todos', { text })

// 刪除 todo
export const removeTodo = (id) => api.delete(`/todos/${id}`)

// 狀態切換 todo
export const toggleTodo = (id) => api.patch(`/todos/${id}/toggle`)

// 取得使用者的 todos
export const getTodos = () => api.get('/todos')


