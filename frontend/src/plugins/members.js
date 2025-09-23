import api from './api'

// 註冊
export const register = (data) => api.post('/members/register', data)

// 登入
export const login = (data) => api.post('/members/login', data)

// 驗證身分(重設密碼第一步)
export const verify = (data) => api.post('/members/verify', data)

// 重設密碼(重設密碼第二步)
export const retrieve = (data) => api.post('/members/retrieve', data)