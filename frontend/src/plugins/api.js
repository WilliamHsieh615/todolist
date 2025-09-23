import axios from 'axios'

const api = axios.create({
    baseURL: '/api',
    timeout: 5000,
    withCredentials: true, // 後端 cookie 設定
})

// 回應攔截器：可以統一錯誤處理
api.interceptors.response.use(
    response => response,
    error => {
        console.error('API 錯誤：', error.response?.data || error.message)
        return Promise.reject(error)
    }
)

export default api