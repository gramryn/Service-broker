package servicebroker.model.fixture;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import org.openpaas.servicebroker.model.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ServiceInstanceFixture {

	public static List<ServiceInstance> getAllServiceInstances() {
		List<ServiceInstance> instances = new ArrayList<ServiceInstance>();
		instances.add(getServiceInstance());
		instances.add(getServiceInstanceTwo());
		return instances;
	}
	
	public static ServiceInstance getServiceInstance() {
		return new ServiceInstance(new CreateServiceInstanceRequest(
				"service-one-id", 
				"plan-one-id", 
				DataFixture.getOrgOneGuid(), 
				DataFixture.getSpaceOneGuid()).withServiceInstanceId("service-instnce-one-id"))
			.withDashboardUrl("dashboard_url");
				
	}
	
	public static ServiceInstance getServiceInstanceTwo() {
		return new ServiceInstance(new CreateServiceInstanceRequest(
				"service-two-id", 
				"plan-two-id", 
				DataFixture.getOrgOneGuid(), 
				DataFixture.getSpaceOneGuid()).withServiceInstanceId("service-instnce-two-id"))
			.withDashboardUrl("dashboard_url");

	}
	
	public static String getServiceInstanceId() {
		return "service-instance-id";
	}
	
	public static CreateServiceInstanceRequest getCreateServiceInstanceRequest() {
		ServiceDefinition service = ServiceFixture.getService();
		return new CreateServiceInstanceRequest(
				service.getId(), 
				service.getPlans().get(0).getId(),
				DataFixture.getOrgOneGuid(),
				DataFixture.getSpaceOneGuid()
		);
	}
	
	public static String getCreateServiceInstanceRequestJson() throws IOException {
		 return DataFixture.toJson(getCreateServiceInstanceRequest());
	}
		
	public static CreateServiceInstanceResponse getCreateServiceInstanceResponse() {
		return new CreateServiceInstanceResponse(getServiceInstance());
	}

	public static String getUpdateServiceInstanceRequestJson() throws
			IOException {
		return DataFixture.toJson(getUpdateServiceInstanceRequest());
	}
	
	public static UpdateServiceInstanceRequest getUpdateServiceInstanceRequest() {
		ServiceDefinition service = ServiceFixture.getService();
		return new UpdateServiceInstanceRequest(service.getPlans().get(0).getId());
	}


}
