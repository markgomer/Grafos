import scrapy

class CountrypopSpider(scrapy.Spider):
    LOG_LEVEL = 'INFO'
    name = 'wiki'
    allowed_domains = ['wikipedia.org']
    start_urls = ['https://en.wikipedia.org/wiki/List_of_sovereign_states_in_the_2020s']


    def parse(self, response):
        coutries=200
        cnames=['Australia','Bhutan']
        for i in range(5,coutries):
            for title in response.xpath('//*[@id="mw-content-text"]/div/table[1]/tbody/tr['+str(i+2)+']/td[1]/b/a'):
                name=title.css('a ::text').get()
                yield {name:'https://en.wikipedia.org'+title.css('a').get().split("\"")[1]}