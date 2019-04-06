package com.denma.planeat;

import com.denma.planeat.utils.api.ApiService;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static org.junit.Assert.assertEquals;

public class EdamamTest {

    private MockWebServer mockWebServer;

    @Before
    public void setup() throws IOException {
        mockWebServer = new MockWebServer();
        mockWebServer.start();
    }

    @After
    public void shutDown() throws IOException {
        mockWebServer.shutdown();
    }

    @Test
    public void EdamamTest() throws IOException{

        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(mockWebServer.url("").toString())
                .build();

        mockWebServer.enqueue(new MockResponse().setBody(loadJSONFromResources("response.json")));

        final String[] label = new String[1];
        final String[] image = new String[1];
        final String[] url = new String[1];
        final List<String>[] ingredientLines = new List[]{new ArrayList<>()};

        ApiService service = retrofit.create(ApiService.class);

        CountDownLatch latch = new CountDownLatch(1);
        service.getRecipes("riz", null, null).enqueue(new Callback<com.denma.planeat.models.remote.Response>() {
            @Override
            public void onResponse(Call<com.denma.planeat.models.remote.Response> call, Response<com.denma.planeat.models.remote.Response> response) {
                label[0] = response.body().getHits().get(0).getRecipe().getLabel();
                image[0] = response.body().getHits().get(0).getRecipe().getImage();
                url[0] = response.body().getHits().get(0).getRecipe().getUrlSource();
                ingredientLines[0] = response.body().getHits().get(0).getRecipe().getIngredientLines();
                latch.countDown();
            }

            @Override
            public void onFailure(Call<com.denma.planeat.models.remote.Response> call, Throwable t) {
                latch.countDown();
            }
        });

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        assertEquals("Riz au lait", label[0]);
        assertEquals("https://www.edamam.com/web-img/133/133b4b0e3ac2fac6a1021b68f19d0994.jpg", image[0]);
        assertEquals("https://food52.com/recipes/415-riz-au-lait", url[0]);
        assertEquals("4 cups whole milk", ingredientLines[0].get(0));
        assertEquals("1/3 cup arborio rice", ingredientLines[0].get(1));
        assertEquals("1/3 cup sugar", ingredientLines[0].get(2));
        assertEquals("1 vanilla bean", ingredientLines[0].get(3));
    }

    public String loadJSONFromResources(String fileName) {
        String json = null;
        try {
            InputStream is = this.getClass().getClassLoader().getResourceAsStream(fileName);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

}
