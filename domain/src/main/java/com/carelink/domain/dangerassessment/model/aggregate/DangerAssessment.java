package com.carelink.domain.dangerassessment.model.aggregate;

import java.time.LocalDateTime;
import java.util.Objects;

import com.carelink.domain.appointment.model.valueobject.AppointmentId;
import com.carelink.domain.common.model.AggregateRoot;
import com.carelink.domain.dangerassessment.event.DangerAssessmentRegisteredEvent;
import com.carelink.domain.dangerassessment.event.DangerAssessmentUpdatedEvent;
import com.carelink.domain.dangerassessment.model.valueobject.DangerAssessmentId;
import com.carelink.domain.dangerlevel.model.valueobject.DangerLevelId;
import com.carelink.domain.specialist.model.valueobject.SpecialistId;

public class DangerAssessment extends AggregateRoot {
    private final DangerAssessmentId id;
    private AppointmentId appointmentId;
    private DangerLevelId dangerLevelId;
    private Boolean suicidalIdeation;
    private Boolean hasSuicidePlan;
    private Boolean hasSuicideIntent;
    private Boolean selfHarmBehavior;
    private Boolean riskToOthers;
    private String protectiveFactors;
    private String identifiedRiskFactors;
    private String clinicalActionsTaken;
    private String evaluatorObservations;
    private final LocalDateTime evaluatedAt;
    private SpecialistId evaluatedBy;

    private DangerAssessment(
            DangerAssessmentId id,
            AppointmentId appointmentId,
            DangerLevelId dangerLevelId,
            Boolean suicidalIdeation,
            Boolean hasSuicidePlan,
            Boolean hasSuicideIntent,
            Boolean selfHarmBehavior,
            Boolean riskToOthers,
            String protectiveFactors,
            String identifiedRiskFactors,
            String clinicalActionsTaken,
            String evaluatorObservations,
            LocalDateTime evaluatedAt,
            SpecialistId evaluatedBy) {
        this.id = Objects.requireNonNull(id, "id must not be null");
        this.appointmentId = Objects.requireNonNull(appointmentId, "appointmentId must not be null");
        this.dangerLevelId = Objects.requireNonNull(dangerLevelId, "dangerLevelId must not be null");
        this.suicidalIdeation = Objects.requireNonNull(suicidalIdeation, "suicidalIdeation must not be null");
        this.hasSuicidePlan = Objects.requireNonNull(hasSuicidePlan, "hasSuicidePlan must not be null");
        this.hasSuicideIntent = Objects.requireNonNull(hasSuicideIntent, "hasSuicideIntent must not be null");
        this.selfHarmBehavior = Objects.requireNonNull(selfHarmBehavior, "selfHarmBehavior must not be null");
        this.riskToOthers = Objects.requireNonNull(riskToOthers, "riskToOthers must not be null");
        this.protectiveFactors = protectiveFactors;
        this.identifiedRiskFactors = identifiedRiskFactors;
        this.clinicalActionsTaken = clinicalActionsTaken;
        this.evaluatorObservations = evaluatorObservations;
        this.evaluatedAt = Objects.requireNonNull(evaluatedAt, "evaluatedAt must not be null");
        this.evaluatedBy = Objects.requireNonNull(evaluatedBy, "evaluatedBy must not be null");
    }

    public static DangerAssessment register(
            AppointmentId appointmentId,
            DangerLevelId dangerLevelId,
            Boolean suicidalIdeation,
            Boolean hasSuicidePlan,
            Boolean hasSuicideIntent,
            Boolean selfHarmBehavior,
            Boolean riskToOthers,
            String protectiveFactors,
            String identifiedRiskFactors,
            String clinicalActionsTaken,
            String evaluatorObservations,
            SpecialistId evaluatedBy) {
        DangerAssessmentId id = DangerAssessmentId.generate();
        LocalDateTime now = LocalDateTime.now();
        DangerAssessment aggregate = new DangerAssessment(
                id,
                appointmentId,
                dangerLevelId,
                suicidalIdeation,
                hasSuicidePlan,
                hasSuicideIntent,
                selfHarmBehavior,
                riskToOthers,
                protectiveFactors,
                identifiedRiskFactors,
                clinicalActionsTaken,
                evaluatorObservations,
                now,
                evaluatedBy);
        aggregate.recordEvent(new DangerAssessmentRegisteredEvent(id, now));
        return aggregate;
    }

    public static DangerAssessment restore(
            DangerAssessmentId id,
            AppointmentId appointmentId,
            DangerLevelId dangerLevelId,
            Boolean suicidalIdeation,
            Boolean hasSuicidePlan,
            Boolean hasSuicideIntent,
            Boolean selfHarmBehavior,
            Boolean riskToOthers,
            String protectiveFactors,
            String identifiedRiskFactors,
            String clinicalActionsTaken,
            String evaluatorObservations,
            LocalDateTime evaluatedAt,
            SpecialistId evaluatedBy) {
        return new DangerAssessment(
                id,
                appointmentId,
                dangerLevelId,
                suicidalIdeation,
                hasSuicidePlan,
                hasSuicideIntent,
                selfHarmBehavior,
                riskToOthers,
                protectiveFactors,
                identifiedRiskFactors,
                clinicalActionsTaken,
                evaluatorObservations,
                evaluatedAt,
                evaluatedBy);
    }

    public void update(
            AppointmentId appointmentId,
            DangerLevelId dangerLevelId,
            Boolean suicidalIdeation,
            Boolean hasSuicidePlan,
            Boolean hasSuicideIntent,
            Boolean selfHarmBehavior,
            Boolean riskToOthers,
            String protectiveFactors,
            String identifiedRiskFactors,
            String clinicalActionsTaken,
            String evaluatorObservations,
            SpecialistId evaluatedBy) {
        this.appointmentId = Objects.requireNonNull(appointmentId, "appointmentId must not be null");
        this.dangerLevelId = Objects.requireNonNull(dangerLevelId, "dangerLevelId must not be null");
        this.suicidalIdeation = Objects.requireNonNull(suicidalIdeation, "suicidalIdeation must not be null");
        this.hasSuicidePlan = Objects.requireNonNull(hasSuicidePlan, "hasSuicidePlan must not be null");
        this.hasSuicideIntent = Objects.requireNonNull(hasSuicideIntent, "hasSuicideIntent must not be null");
        this.selfHarmBehavior = Objects.requireNonNull(selfHarmBehavior, "selfHarmBehavior must not be null");
        this.riskToOthers = Objects.requireNonNull(riskToOthers, "riskToOthers must not be null");
        this.protectiveFactors = protectiveFactors;
        this.identifiedRiskFactors = identifiedRiskFactors;
        this.clinicalActionsTaken = clinicalActionsTaken;
        this.evaluatorObservations = evaluatorObservations;
        this.evaluatedBy = Objects.requireNonNull(evaluatedBy, "evaluatedBy must not be null");
        LocalDateTime now = LocalDateTime.now();
        recordEvent(new DangerAssessmentUpdatedEvent(
                this.id,
                this.appointmentId,
                this.dangerLevelId,
                this.suicidalIdeation,
                this.hasSuicidePlan,
                this.hasSuicideIntent,
                this.selfHarmBehavior,
                this.riskToOthers,
                this.protectiveFactors,
                this.identifiedRiskFactors,
                this.clinicalActionsTaken,
                this.evaluatorObservations,
                this.evaluatedBy,
                now));
    }

    public DangerAssessmentId id() {
        return id;
    }

    public AppointmentId appointmentId() {
        return appointmentId;
    }

    public DangerLevelId dangerLevelId() {
        return dangerLevelId;
    }

    public Boolean suicidalIdeation() {
        return suicidalIdeation;
    }

    public Boolean hasSuicidePlan() {
        return hasSuicidePlan;
    }

    public Boolean hasSuicideIntent() {
        return hasSuicideIntent;
    }

    public Boolean selfHarmBehavior() {
        return selfHarmBehavior;
    }

    public Boolean riskToOthers() {
        return riskToOthers;
    }

    public String protectiveFactors() {
        return protectiveFactors;
    }

    public String identifiedRiskFactors() {
        return identifiedRiskFactors;
    }

    public String clinicalActionsTaken() {
        return clinicalActionsTaken;
    }

    public String evaluatorObservations() {
        return evaluatorObservations;
    }

    public LocalDateTime evaluatedAt() {
        return evaluatedAt;
    }

    public SpecialistId evaluatedBy() {
        return evaluatedBy;
    }
}
