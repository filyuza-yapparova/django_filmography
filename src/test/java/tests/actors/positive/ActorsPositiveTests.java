package tests.actors.positive;

import constants.Constant.NotificationType;
import entities.Actor;
import org.junit.jupiter.api.Test;
import tests.base.BaseTest;

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
    public void addNewActor() {
        basePage.goToUrl(ACTORS_URL);
        Actor actor = Actor.createTomCruise();
        actorsPage.addNewActor(actor)
                .saveActor()
                .isActorPresent(actor)
                .notificationIsShown(NotificationType.ADD, actor)
                .deleteSingleActor(actor)
                .actorDeletedSuccessfullyNotificationIsShown();
    }

    @Test
    public void deleteAllActors() {
        basePage.goToUrl(ACTORS_URL);
        for (int i = 0; i < 7; i++) {
            actorsPage.createActorAndSave(Actor.createFakeActor());
        }
        actorsPage.deleteAllActorsAndCheckDeletedCount();
    }

    @Test
    public void editExistingActor() {
        basePage.goToUrl(ACTORS_URL);
        Actor actor = Actor.createTomCruise();
        actorsPage.addNewActor(actor)
                .saveActor()
                .isActorPresent(actor)
                .notificationIsShown(NotificationType.ADD, actor);
        Actor updatedActor = Actor.createFakeActor();
        actorsPage.editActor(actor, updatedActor)
                .notificationIsShown(NotificationType.EDIT, updatedActor)
                .isActorPresent(updatedActor)
                .deleteSingleActor(updatedActor)
                .actorDeletedSuccessfullyNotificationIsShown();
    }
}
