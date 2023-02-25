package hu.fp.demoproject.service;

import hu.fp.demoproject.database.entity.UrlStore;
import hu.fp.demoproject.database.repository.UrlStoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ShortenerService {

    @Value("${app.base_path}")
    String basePath;

    @Autowired
    UrlStoreRepository urlRepo;

    public String shorten(String url) {
        UrlStore stored = urlRepo.findByOriginal(url)
                                 .orElseGet(() -> saveAndEncode(url));
        return basePath + "/" + stored.getShortened();
    }

    public String decode(String shortUrl) throws Exception {
        Long id = decodeUrl(shortUrl);
        UrlStore stored = urlRepo.findById(id)
                                 .orElseThrow(() -> new Exception(""));
        return stored.getOriginal();
    }

    private UrlStore saveAndEncode(String url) {
        UrlStore elem = new UrlStore(url);
        elem = urlRepo.save(elem);
        elem.setShortened(encode(elem.getId()));
        return urlRepo.save(elem);
    }

    private String encode(long id) {
        char map[] = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();
        StringBuffer shortUrl = new StringBuffer();

        while (id > 0)
        {
            shortUrl.append(map[(int)(id % 62)]);
            id = id / 62;
        }

        return shortUrl.reverse().toString();
    }

    private long decodeUrl(String shortUrl) {
        long id = 0;

        for (int i = 0; i < shortUrl.length(); i++) {
            if ('a' <= shortUrl.charAt(i) && shortUrl.charAt(i) <= 'z')
                id = id * 62 + shortUrl.charAt(i) - 'a';
            if ('A' <= shortUrl.charAt(i) && shortUrl.charAt(i) <= 'Z')
                id = id * 62 + shortUrl.charAt(i) - 'A' + 26;
            if ('0' <= shortUrl.charAt(i) && shortUrl.charAt(i) <= '9')
                id = id * 62 + shortUrl.charAt(i) - '0' + 52;
        }
        return id;
    }
}
