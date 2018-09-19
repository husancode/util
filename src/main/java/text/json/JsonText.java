package text.json;

/**
 * <p>
 * </p>
 *
 * @author hukajie
 * @version V1.0
 * @Date 2017/9/22
 * @modify by user: {修改人}
 * @modify by reason:{方法名}:{原因}
 */
public class JsonText {
    public static final String EXAMPLE1 = "{\n" +
            "  \"requestId\": \"ff36a3cc-ec34-11e6-b1a0-64510650abcf\",\n" +
            "  \"payload\": {\n" +
            "    \"agentUserId\": \"1836.15267389\",\n" +
            "    \"devices\": [{\n" +
            "      \"id\": \"123\",\n" +
            "      \"type\": \"action.devices.types.OUTLET\",\n" +
            "      \"traits\": [\n" +
            "        \"action.devices.traits.OnOff\"\n" +
            "      ],\n" +
            "      \"name\": {\n" +
            "        \"defaultNames\": [\"My Outlet 1234\"],\n" +
            "        \"name\": \"Night light\",\n" +
            "        \"nicknames\": [\"wall plug\"]\n" +
            "      },\n" +
            "      \"willReportState\": true,\n" +
            "        \"deviceInfo\": {\n" +
            "          \"manufacturer\": \"lights-out-inc\",\n" +
            "          \"model\": \"hs1234\",\n" +
            "          \"hwVersion\": \"3.2\",\n" +
            "          \"swVersion\": \"11.4\"\n" +
            "        },\n" +
            "        \"customData\": {\n" +
            "          \"fooValue\": 74,\n" +
            "          \"barValue\": true,\n" +
            "          \"bazValue\": \"foo\"\n" +
            "        }\n" +
            "    },{\n" +
            "      \"id\": \"456\",\n" +
            "      \"type\": \"action.devices.types.LIGHT\",\n" +
            "        \"traits\": [\n" +
            "          \"action.devices.traits.OnOff\", \"action.devices.traits.Brightness\",\n" +
            "          \"action.devices.traits.ColorTemperature\",\n" +
            "          \"action.devices.traits.ColorSpectrum\"\n" +
            "        ],\n" +
            "        \"name\": {\n" +
            "          \"defaultNames\": [\"lights out inc. bulb A19 color hyperglow\"],\n" +
            "          \"name\": \"lamp1\",\n" +
            "          \"nicknames\": [\"reading lamp\"]\n" +
            "        },\n" +
            "        \"willReportState\": true,\n" +
            "        \"attributes\": {\n" +
            "          \"temperatureMinK\": 2000,\n" +
            "          \"temperatureMaxK\": 6500\n" +
            "        },\n" +
            "        \"deviceInfo\": {\n" +
            "          \"manufacturer\": \"lights out inc.\",\n" +
            "          \"model\": \"hg11\",\n" +
            "          \"hwVersion\": \"1.2\",\n" +
            "          \"swVersion\": \"5.4\"\n" +
            "        },\n" +
            "        \"customData\": {\n" +
            "          \"fooValue\": 12,\n" +
            "          \"barValue\": false,\n" +
            "          \"bazValue\": \"bar\"\n" +
            "        }\n" +
            "      }]\n" +
            "  }\n" +
            "}";
}
