# coding=utf8
from bs4 import BeautifulSoup
import requests

page = 'https://en.wikipedia.org/wiki/Quicksort'

r = requests.get(page)

soup = BeautifulSoup(r.text, 'html.parser')
soup = soup.find(id='mw-content-text')

links = []
for link in soup.find_all('a'):
    try:
        matches = ['http','://','#',':','(identifier)']
        if '/wiki/' in link.get('href') and not any(x in link.get('href') for x in matches):
            links.append('https://en.wikipedia.org'+link.get('href'))
    except:
        continue

[print(l) for l in links]


