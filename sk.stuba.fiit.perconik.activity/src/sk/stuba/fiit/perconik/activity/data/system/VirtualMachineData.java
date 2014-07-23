package sk.stuba.fiit.perconik.activity.data.system;

public class VirtualMachineData extends SpecificationData {
  protected SpecificationData specification;

  public VirtualMachineData() {}

  public void setSpecification(SpecificationData specification) {
    this.specification = specification;
  }

  public SpecificationData getSpecification() {
    return this.specification;
  }
}
