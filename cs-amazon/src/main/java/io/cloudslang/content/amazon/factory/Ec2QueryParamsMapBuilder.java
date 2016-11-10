package io.cloudslang.content.amazon.factory;

import io.cloudslang.content.amazon.entities.inputs.InputsWrapper;
import io.cloudslang.content.amazon.factory.helpers.*;

import java.util.Map;

import static io.cloudslang.content.amazon.entities.constants.Constants.Ec2QueryApiActions.*;

/**
 * Created by TusaM
 * 11/7/2016.
 */
class Ec2QueryParamsMapBuilder {
    private static final String UNSUPPORTED_QUERY_API = "Unsupported Query API.";

    private Ec2QueryParamsMapBuilder() {
    }

    static Map<String, String> getEc2QueryParamsMap(InputsWrapper wrapper) {
        switch (wrapper.getCommonInputs().getAction()) {
            case ALLOCATE_ADDRESS:
                return new ElasticIpUtils().getAllocateAddressQueryParamsMap(wrapper);
            case ASSOCIATE_ADDRESS:
                return new NetworkUtils().getAssociateAddressQueryParamsMap(wrapper);
            case ATTACH_NETWORK_INTERFACE:
                return new NetworkUtils().getAttachNetworkInterfaceQueryParamsMap(wrapper);
            case ATTACH_VOLUME:
                return new VolumeUtils().getAttachVolumeQueryParamsMap(wrapper);
            case CREATE_IMAGE:
                return new ImageUtils().getCreateImageQueryParamsMap(wrapper);
            case CREATE_NETWORK_INTERFACE:
                return new NetworkUtils().getCreateNetworkInterfaceQueryParamsMap(wrapper);
            case CREATE_SNAPSHOT:
                return new SnapshotUtils().getCreateSnapshotQueryParamsMap(wrapper);
            case CREATE_TAGS:
                return new TagUtils().getCreateTagsQueryParamsMap(wrapper);
            case CREATE_VOLUME:
                return new VolumeUtils().getCreateVolumeQueryParamsMap(wrapper);
            case DELETE_NETWORK_INTERFACE:
                return new NetworkUtils().getDeleteNetworkInterfaceQueryParamsMap(wrapper);
            case DELETE_SNAPSHOT:
                return new SnapshotUtils().getDeleteSnapshotQueryParamsMap(wrapper);
            case DELETE_VOLUME:
                return new VolumeUtils().getDeleteVolumeQueryParamsMap(wrapper);
            case DESCRIBE_AVAILABILITY_ZONES:
                return new RegionUtils().getDescribeAvailabilityZonesQueryParamsMap(wrapper);
            case DESCRIBE_IMAGES:
                return new ImageUtils().getDescribeImagesQueryParamsMap(wrapper);
            case DESCRIBE_IMAGE_ATTRIBUTE:
                return new ImageUtils().getDescribeImageAttributeQueryParamsMap(wrapper);
            case DESCRIBE_INSTANCES:
                return new InstanceUtils().getDescribeInstancesQueryParamsMap(wrapper);
            case DESCRIBE_REGIONS:
                return new RegionUtils().getDescribeRegionsQueryParamsMap(wrapper);
            case DEREGISTER_IMAGE:
                return new ImageUtils().getDeregisterImageQueryParamsMap(wrapper);
            case DETACH_NETWORK_INTERFACE:
                return new NetworkUtils().getDetachNetworkInterfaceQueryParamsMap(wrapper);
            case DETACH_VOLUME:
                return new VolumeUtils().getDetachVolumeQueryParamsMap(wrapper);
            case DISASSOCIATE_ADDRESS:
                return new NetworkUtils().getDisassociateAddressQueryParamsMap(wrapper);
            case MODIFY_IMAGE_ATTRIBUTE:
                return new ImageUtils().getModifyImageAttributeQueryParamsMap(wrapper);
            case MODIFY_INSTANCE_ATTRIBUTE:
                return new InstanceUtils().getModifyInstanceAttributeQueryParamsMap(wrapper);
            case REBOOT_INSTANCES:
                return new InstanceUtils().getRebootInstancesQueryParamsMap(wrapper);
            case RELEASE_ADDRESS:
                return new ElasticIpUtils().getReleaseAddressQueryParamsMap(wrapper);
            case RESET_IMAGE_ATTRIBUTE:
                return new ImageUtils().getResetImageAttributeQueryParamsMap(wrapper);
            case RUN_INSTANCES:
                return new InstanceUtils().getRunInstancesQueryParamsMap(wrapper);
            case START_INSTANCES:
                return new InstanceUtils().getStartInstancesQueryParamsMap(wrapper);
            case STOP_INSTANCES:
                return new InstanceUtils().getStopInstancesQueryParamsMap(wrapper);
            case TERMINATE_INSTANCES:
                return new InstanceUtils().getTerminateInstancesQueryParamsMap(wrapper);
            default:
                throw new RuntimeException(UNSUPPORTED_QUERY_API);
        }
    }
}