export const setCookie = (
  name,
  value,
  days = 1,
  { path = '/', sameSite = 'Strict', secure = location.protocol === 'https:', domain } = {}
) => {
  const expires = new Date(Date.now() + days * 864e5).toUTCString();
  let cookie = `${encodeURIComponent(name)}=${encodeURIComponent(value)}; expires=${expires}; path=${path}; SameSite=${sameSite}`;
  if (secure) cookie += '; Secure';
  if (domain) cookie += `; domain=${domain}`;
  document.cookie = cookie;
};

export const getCookie = (name) => {
  const key = encodeURIComponent(name) + '=';
  const found = document.cookie.split('; ').find(row => row.startsWith(key));
  return found ? decodeURIComponent(found.slice(key.length)) : null;
};

export const deleteCookie = (name, options = {}) => setCookie(name, '', -1, options);
