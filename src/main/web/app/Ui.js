import Generator from "./Generator";

export default class Ui {
    static initializeDefaults() {
        $(".menu .item").tab();

        $("#url-type").dropdown({
            action: "activate",
            onChange: (value) => {
                if (value === "short") {
                    Generator.createOrGetShortUrl(Ui.displayUrl);
                } else {
                    Ui.displayUrl(Generator.longUrl);
                }
            }
        }).dropdown("save defaults");

        $("#generate-button").click(() => {
            Ui.initializeOntologyUrlInput();
            Generator.generateAndDisplay();
        });

        $("#select-all-button").click(() => Ui.features.prop("checked", true));
        $("#select-none-button").click(() => Ui.features.prop("checked", false));
        $("#invert-selection-button").click(() => Ui.features.each(function () {
            $(this).prop("checked", !$(this).prop("checked"));
        }));
    }

    static initializeFormats(defaultFormat) {
        let element = $("#format-dropdown");
        if (defaultFormat) {
            element.dropdown("set selected", defaultFormat.extension);
        } else {
            element.dropdown();
        }
    }

    static initializeCheckboxes() {
        $(".checkbox").checkbox();
    }

    static displayUrl(url) {
        $("#ontology-url").val(url);
        $("#download-button").attr("href", url);
    }

    static get selectedExtension() {
        var extension = $("#format-list").find(".selected.item").attr("data-value");
        if (extension) {
            extension = "." + extension;
        } else {
            extension = "";
        }
        return extension;
    }

    static get selectedFeatures() {
        var selectedCheckboxes = Ui.featureContainer.find(":checked").parent();

        var features = selectedCheckboxes.map(function () {
            return $(this).data();
        });

        return features.get();
    }

    static initializeOntologyUrlInput() {
        $("#url-type").removeClass("disabled").dropdown("restore defaults");
        $("#download-button").removeClass("disabled");
    }

    static get featureContainer() {
        return $("#feature-container");
    }

    static get features() {
        return Ui.featureContainer.find(":checkbox");
    }
}
