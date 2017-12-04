package in.ankushs.browscap4j;

import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import in.ankushs.browscap4j.domain.BrowserCapabilities;
import in.ankushs.browscap4j.repository.Repository;
import in.ankushs.browscap4j.repository.TrieRepository;
import in.ankushs.browscap4j.utils.PreConditions;

import java.io.File;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by ankushsharma on 04/12/17.
 */
public class Browscap implements IBrowscap {

    private final ListeningExecutorService listeningExecutor;
    private static final Repository repo = TrieRepository.getInstance();

    private final ExecutorService executorService;

    public Browscap(final File csvFile) {
        PreConditions.notNull(csvFile, "csvFile cannot be null");

        this.executorService =  Executors.newSingleThreadExecutor();
        this.listeningExecutor = MoreExecutors.listeningDecorator(executorService);
        repo.build(csvFile);
    }

    public Browscap(final File csvFile, final ExecutorService executorService) {
        PreConditions.notNull(csvFile, "csvFile cannot be null");
        PreConditions.notNull(executorService, "executorService cannot be null");

        if(!csvFile.exists()){
            throw new IllegalArgumentException("file " + csvFile.getAbsolutePath()  + " does not exist");
        }

        this.executorService = executorService;
        this.listeningExecutor = MoreExecutors.listeningDecorator(executorService);
        repo.build(csvFile);
    }


    @Override
    public BrowserCapabilities lookup(final String userAgent) {
        PreConditions.notEmptyString(userAgent, "userAgent cannot be null");
        return repo.lookup(userAgent);
    }

    @Override
    public ListenableFuture<BrowserCapabilities> lookupAsync(final String userAgent) {
        final Callable<BrowserCapabilities> asyncTask = () -> lookup(userAgent);
        return listeningExecutor.submit(asyncTask);
    }
}
