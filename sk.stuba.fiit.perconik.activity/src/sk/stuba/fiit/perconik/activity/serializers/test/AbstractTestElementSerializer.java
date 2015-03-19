package sk.stuba.fiit.perconik.activity.serializers.test;

import org.eclipse.jdt.junit.model.ITestCaseElement;
import org.eclipse.jdt.junit.model.ITestElement;
import org.eclipse.jdt.junit.model.ITestElementContainer;
import org.eclipse.jdt.junit.model.ITestRunSession;
import org.eclipse.jdt.junit.model.ITestSuiteElement;

import sk.stuba.fiit.perconik.activity.serializers.AbstractConfigurableMultiSerializer;
import sk.stuba.fiit.perconik.data.content.StructuredContent;

import static sk.stuba.fiit.perconik.activity.serializers.Serializations.putObjectIdentity;
import static sk.stuba.fiit.perconik.activity.serializers.test.TestCaseElementSerializer.putTestCaseElement;
import static sk.stuba.fiit.perconik.activity.serializers.test.TestElementContainerSerializer.putTestElementContainer;
import static sk.stuba.fiit.perconik.activity.serializers.test.TestElementSerializer.putTestElement;
import static sk.stuba.fiit.perconik.activity.serializers.test.TestRunSessionSerializer.putTestRunSession;
import static sk.stuba.fiit.perconik.activity.serializers.test.TestSuiteElementSerializer.putTestSuiteElement;

abstract class AbstractTestElementSerializer<T extends ITestElement> extends AbstractConfigurableMultiSerializer<T> {
  AbstractTestElementSerializer(final Option ... options) {
    super(options);
  }

  AbstractTestElementSerializer(final Iterable<Option> options) {
    super(options);
  }

  @Override
  protected final void put(final StructuredContent content, final T element) {
    putObjectIdentity(content, element);
    putTestElement(content, element, this.options);

    if (element instanceof ITestCaseElement) {
      putTestCaseElement(content, (ITestCaseElement) element, this.options);
    }

    if (element instanceof ITestElementContainer) {
      putTestElementContainer(content, (ITestElementContainer) element, this.options);

      if (element instanceof ITestRunSession) {
        putTestRunSession(content, (ITestRunSession) element, this.options);
      }

      if (element instanceof ITestSuiteElement) {
        putTestSuiteElement(content, (ITestSuiteElement) element, this.options);
      }
    }
  }
}
