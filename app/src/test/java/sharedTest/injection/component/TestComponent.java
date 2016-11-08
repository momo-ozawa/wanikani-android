package sharedTest.injection.component;

import com.mozawa.wanikaniandroid.injection.component.ApplicationComponent;

import javax.inject.Singleton;

import dagger.Component;
import sharedTest.injection.module.ApplicationTestModule;

@Singleton
@Component(modules = ApplicationTestModule.class)
public interface TestComponent extends ApplicationComponent {

}
