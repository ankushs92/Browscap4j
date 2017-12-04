package in.ankushs.browscap4j;

import com.google.common.util.concurrent.ListenableFuture;
import in.ankushs.browscap4j.domain.BrowserCapabilities;

/**
 * Created by ankushsharma on 04/12/17.
 */
public interface IBrowscap {

    BrowserCapabilities lookup(String userAgent);

    ListenableFuture<BrowserCapabilities> lookupAsync(String userAgent);
}
