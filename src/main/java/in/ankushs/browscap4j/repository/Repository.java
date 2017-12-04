package in.ankushs.browscap4j.repository;

import in.ankushs.browscap4j.domain.BrowserCapabilities;

import java.io.File;

/**
 * Created by ankushsharma on 05/12/17.
 */
public interface Repository {

    void build(File file);

    BrowserCapabilities lookup(String userAgent);

}
