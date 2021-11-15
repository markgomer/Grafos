import requests
from bs4 import BeautifulSoup

## CONFIG ##
init_page = 'https://en.wikipedia.org/wiki/Graph_(discrete_mathematics)'
min_nodes = 5000
min_edges = 40000
##

pages = [[init_page]]
nodes = []
edges = []


def search(page: str):
    links = []

    response = requests.get(page)
    soup = BeautifulSoup(response.text, 'html.parser')
    soup = soup.find(id='mw-content-text')

    for link in soup.find_all('a'):
        try:
            matches = ['http','://','#',':','(identifier)']
            if '/wiki/' in link.get('href') and not any(x in link.get('href') for x in matches):
                links.append('https://en.wikipedia.org'+link.get('href'))
        except:
            continue

    return links

def verify_len():
    if len(nodes) > min_nodes and len(edges) > min_edges:
        print("ended")
        return False
    else:
        return True

run = True

while( run ):
    
    links = pages.pop(0)

    for link in links:
        if not(verify_len()):
                run = False
                break
        
        if link not in nodes:
            nodes.append(link)

        links_page = search(link)

        for lp in links_page:
            if not(verify_len()):
                run = False
                break

            e = []

            e.append(link)
            e.append(lp)
            edges.append(e)

            if lp not in nodes:
                nodes.append(link)

            print(len(nodes),len(edges))

        pages.append(links_page)
    
    if not(verify_len()):
                run = False
                break
    
    print(len(nodes),len(edges))


file = open("src/dados/wiki.txt", "w")

for e in edges:
    file.write((e[0]+','+e[1])+'\n')

file.close()

