package fr.soat.soimmo.modules;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import fr.soat.soimmo.controllers.AppController;
import fr.soat.soimmo.controllers.AppControllerImpl;
import fr.soat.soimmo.events.EventBus;
import fr.soat.soimmo.events.EventBusImpl;

@Module(
        injects = fr.soat.soimmo.SoImmo.class,
        includes = LoginModule.class
)
public class AppModule {

    private final fr.soat.soimmo.SoImmo app;

    public AppModule(fr.soat.soimmo.SoImmo app) {
        this.app = app;
    }

    @Provides
    @Singleton
    AppController provideAppController(AppControllerImpl appController) {
        return appController;
    }

    @Provides
    @Singleton
    @ForApplication
    Context provideApplicationContext() {
        return app;
    }

    @Provides
    @Singleton
    @ForApplication
    EventBus provideEventBus() {
        return new EventBusImpl(de.greenrobot.event.EventBus.getDefault());
    }

    /*
    @Provides
    @Singleton
    SQLiteOpenHelper provideDatabaseOpenHelper(@ForApplication Context context) {
        try {
            return new LocalSQLiteOpenHelper(context, "soimmo_db", null, VersionUtils.getVersionCode(context));
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
*/
}
