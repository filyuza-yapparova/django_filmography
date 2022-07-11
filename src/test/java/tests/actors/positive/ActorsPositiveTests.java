package tests.actors.positive;

import entities.Actor;
import org.junit.jupiter.api.Test;
import tests.base.BaseTest;

import java.time.LocalDate;

import static constants.Constant.Urls.ACTORS_URL;

public class ActorsPositiveTests extends BaseTest {

    @Test
    public void openActorsPage() {
        basePage.openPage(basePage.actorsPageButton);
        actorsPage.isEntryAdditionButtonPresent();
        /**
         * actors, movies, characters or directors
         */
        basePage.isPageTitleChanged("actors");
    }

    @Test
    public void addingNewActor() {
        basePage.goToUrl(ACTORS_URL);
        var actor = Actor.builder()
                .firstname("Thomas")
                .lastname("Cruise")
                .birthdate(LocalDate.of(1962, 7, 3))
                .gender("Male")
                .build();
        actorsPage.addNewActor(actor);

//        actorsPage.addNewActor(new Actor("Thomas", "Cruise", LocalDate.of(1962, 7, 3), "Male"));
        actorsPage.saveActor();
        actorsPage.isAddedActorPresent(actor);
        actorsPage.actorAddedSuccessfullyNotificationIsShown(actor);
        actorsPage.deleteAddedActor();
        actorsPage.actorDeletedSuccessfullyNotificationIsShown(actor);
    }
}
