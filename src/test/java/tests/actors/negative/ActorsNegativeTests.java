package tests.actors.negative;

import constants.Constant;
import entities.Actor;
import org.junit.jupiter.api.Test;
import tests.base.BaseTest;

import static constants.Constant.Urls.ACTORS_URL;

public class ActorsNegativeTests extends BaseTest {
    @Test
    public void editExistingActorWithInvalidValue() {
        basePage.goToUrl(ACTORS_URL);
        Actor actor = Actor.createTomCruise();
        actorsPage.addNewActor(actor)
                .saveActor()
                .isActorPresent(actor)
                .notificationIsShown(Constant.NotificationType.ADD, actor);
        Actor updatedActor = Actor.createFakeInvalidActor();
        actorsPage.editActor(actor, updatedActor)
                .fieldValidationIsShown();
        basePage.goToUrl(ACTORS_URL);
        actorsPage.isActorPresent(actor)
                .deleteSingleActor(actor)
                .actorDeletedSuccessfullyNotificationIsShown();
    }
}
