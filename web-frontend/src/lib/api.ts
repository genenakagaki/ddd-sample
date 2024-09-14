const hostName = import.meta.env.VITE_API_HOSTNAME

export async function post(url: string, body: BodyInit): Promise<object> {
  const response = await fetch(hostName + url, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json'
    },
    body: body
  })
  return response
}

export async function login(username: string, password: string) {
  const response = await fetch(hostName + '/api/login', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/x-www-form-urlencoded'
    },
    body: new URLSearchParams({
      username: username,
      password: password
    })
  })
  return response
}
