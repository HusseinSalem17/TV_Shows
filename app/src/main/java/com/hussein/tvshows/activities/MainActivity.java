package com.hussein.tvshows.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.hussein.tvshows.R;
import com.hussein.tvshows.adapters.TVShowsAdapter;
import com.hussein.tvshows.databinding.ActivityMainBinding;
import com.hussein.tvshows.listeners.TVShowListener;
import com.hussein.tvshows.models.TVShow;
import com.hussein.tvshows.viewmodels.MostPopularTVShowsViewModel;

import java.util.ArrayList;
import java.util.List;

/*
1. TVShow (model)
2. TvShowResponse (Model and Response for the outer data like(page number & numOfPages & listOfTVShows for everyPage)
3. ApiService
4. ApiClient
5. MostPopularTVShowsRepository
6. MostPopularTVShowsViewModel
7. Binding Adapters
8. convert item_container_tv_show to binding layout and make object to set values to Views
9. TVShowsAdapter
10. made in MainActivity (doInitialization & getMostPopularTVShows)
11. added progressBar(isLoadingMore) & Variable(isLoadingMore) in activity_main(layout)
12. edit (getMostPopularTVShows & doInitialization) & make toggleLoading (in MainActivity)

For (show Details link)
(
 لما اجي اعمل ال json لازم لما يبقي عندي كذا عنصر داخل بعض امثل الي فالداخل الاول وبعد كدا امثل الي براه ويبقي object وياخد من الكلاس الي فيها الداتا الي بالداخل دي
وحاجه كمان بيبقي في list of String دي ميتعملهاش كلاس دي بس بتبقي List of String واستقبلهم
 )

13. make Episode class (in models)
14. make TVShowDetails (in models)  (their is some variables didn't but in the TVShowDetails class
    maybe because i didn't need them & some of them are repeated
15. TVShowDetailsResponse (to use Episode & TVShowDetails) classes to like json to contain all the data (in responses)
16. Add show-details to APIService to (network)
17. make TVShowDetailsRepository in repositories package
18. make TVShowDetailsViewModel to control all coming data and deal with it
19. make TVShowDetailsActivity
20. make listeners (package) & make TVShowListener (Listener)
21. Add TVSHowListener to TVShowsAdapter (object) to perform the function of interface when press on the layout
    to show the details
22. put implements TVShowListener here (main) to put the body of the function & edit on TVShowsAdapter (second Parameter)
23. write code in TVShowDetailsActivity
24. make imageSliderAdapter and write code in it
25. edit on TVShowDetailsActivity
26. make EpisodesAdapter in (adapters)
27. Edit TVSHowDetailsActivity

.....  For Room Database (for Watchlist) ......
28. Edit on TVShow (added Entity , PrimaryKey , implementation Serializable) For Room Database
29. make package(dao) and interface (TVShowDao)
29. make package(database) and interface (TVShowsDatabase)
30. Edit onTVShowClicked
31. Edit TVShowDetailsActivity (class) & TVShowDetailsViewModel()
32. Edit TVShowDetailsActivity
33. Make WatchlistActivity (Activity) & WatchlistViewModel (in ViewModel)
34. Edit doInitialization()
35. make WatchlistListener (listener) & WatchlistListener (Adapter)
36. Modify WatchlistActivity()
37. make TempDataHolder (utilities)
38. modify WatchlistActivity()

..... For Search ......
1. Edit ApiService (Adding Search)
2. Make SearchTVShowRepository (repositories Package) & SearchViewModel (ViewModel) & SearchActivity (Activity)
3. Edit doInitialization() & SearchActivity
 */

public class MainActivity extends AppCompatActivity implements TVShowListener {

    private ActivityMainBinding activityMainBinding;
    private MostPopularTVShowsViewModel viewModel;
    private List<TVShow> tvShows = new ArrayList<>();
    private TVShowsAdapter tvShowsAdapter;
    private int currentPage = 1;
    private int totalAvailablePages = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        doInitialization();

    }

    private void doInitialization() {
        activityMainBinding.tvShowsRecyclerView.setHasFixedSize(true);
        viewModel = new ViewModelProvider(this).get(MostPopularTVShowsViewModel.class);
        tvShowsAdapter = new TVShowsAdapter(tvShows, this);
        activityMainBinding.tvShowsRecyclerView.setAdapter(tvShowsAdapter);
        activityMainBinding.tvShowsRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                //1 => for down (Scrolling)
                if (!activityMainBinding.tvShowsRecyclerView.canScrollVertically(1)) {
                    if (currentPage <= totalAvailablePages) {
                        currentPage += 1;
                        getMostPopularTVShows();
                    }
                }
            }
        });
        activityMainBinding.imageWatchList.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), WatchlistActivity.class)));
        activityMainBinding.imageSearch.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(),SearchActivity.class)));
        getMostPopularTVShows();
    }

    private void getMostPopularTVShows() {
//        activityMainBinding.setIsLoading(true);
        toggleLoading();
        //mostPopularTVShowsResponse => object of TVShowResponse
        viewModel.getMostPopularTVShows(currentPage).observe(this, mostPopularTVShowsResponse -> {
//            activityMainBinding.setIsLoading(false);
            toggleLoading();
            if (mostPopularTVShowsResponse != null) {
                totalAvailablePages = mostPopularTVShowsResponse.getTotalPages();
                if (mostPopularTVShowsResponse.getTVShows() != null) {
                    int oldCount = tvShows.size();
                    tvShows.addAll(mostPopularTVShowsResponse.getTVShows());
                    tvShowsAdapter.notifyItemRangeInserted(oldCount, tvShows.size());
                }
            }

        });
    }

    private void toggleLoading() {
        if (currentPage == 1) {
            if (activityMainBinding.getIsLoading() != null && activityMainBinding.getIsLoading()) {
                activityMainBinding.setIsLoading(false);
            } else {
                activityMainBinding.setIsLoading(true);
            }
        } else {
            if (activityMainBinding.getIsLoadingMore() != null && activityMainBinding.getIsLoadingMore()) {
                activityMainBinding.setIsLoadingMore(false);
            } else {
                activityMainBinding.setIsLoadingMore(true);
            }
        }
    }

    @Override
    public void onTVShowClicked(TVShow tvShow) {
        Intent intent = new Intent(getApplicationContext(), TVShowDetailsActivity.class);
        intent.putExtra("tvShow", tvShow);
        startActivity(intent);
    }
}