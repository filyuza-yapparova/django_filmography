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
        actorsPage.addNewActor(actor)

//        actorsPage.addNewActor(new Actor("Thomas", "Cruise", LocalDate.of(1962, 7, 3), "Male"))
                .saveActor()
                .isAddedActorPresent(actor)
                .actorAddedSuccessfullyNotificationIsShown(actor)
                .deleteAddedActor(actor)
                .actorDeletedSuccessfullyNotificationIsShown(actor);
    }

    @Test
    public void deleteAllActors() {
        basePage.goToUrl(ACTORS_URL);
        actorsPage.deleteAllActors()
                .watchActorList();
    }

    @Test
    public void editExistingActor() {
        basePage.goToUrl(ACTORS_URL);
        LocalDate.from(faker.date().birthday(12, 85).toInstant());
        var actor = Actor.builder()
                .firstname(faker.name().firstName())
                .lastname(faker.name().lastName())
                .birthdate(LocalDate.from(faker.date().birthday(12, 85).toInstant()))
                .gender("Male")
                .build();
        actorsPage.addNewActor(actor)
                .saveActor()
                .isAddedActorPresent(actor)
                .actorAddedSuccessfullyNotificationIsShown(actor)
                .editActor(actor)
                .actorEditedSuccessfullyNotificationIsShown(actor);
    }
}
