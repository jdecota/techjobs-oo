package org.launchcode.controllers;

import org.launchcode.models.*;
import org.launchcode.models.Job;
import org.launchcode.models.data.JobFieldData;
import org.launchcode.models.forms.JobForm;
import org.launchcode.models.data.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping(value = "job")
public class JobController {

    private JobData jobData = JobData.getInstance();

    // The detail display for a given Job at URLs like /job?id=17
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index(Model model, int id) {
        Job jobById = jobData.findById(id);
        model.addAttribute("singleJob", jobById);
        // TODO #1 - get the Job with the given ID and pass it into the view
        return "job-detail";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String add(Model model) {
        model.addAttribute(new JobForm());
        return "new-job";
    }

    // TODO #6 - Validate the JobForm model, and if valid, create a

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String add(Model model, @ModelAttribute @Valid JobForm jobForm, Errors errors) {
        // public String addJobForm(@ModelAttribute @Valid Job newJob, Errors errors, Model model) {

        if (errors.hasErrors()) {
            return "new-job";

        } else {
            Job newJob = new Job();
            newJob.setName(jobForm.getName());

            newJob.setEmployer(jobData.getEmployers().findById(jobForm.getEmployerId()));
            newJob.setCoreCompetency(jobData.getCoreCompetencies().findById(jobForm.getCoreCompetencyId()));
            newJob.setLocation(jobData.getLocations().findById(jobForm.getLocationId()));
            newJob.setPositionType(jobData.getPositionTypes().findById(jobForm.getPositionTypeId()));

            jobData.add(newJob);
            //model.addAttribute("singleJob", new Job());
            return "redirect:?id=" + newJob.getId();
        }
    }
            //newJob = JobData.add(Job newJob);
            //or
            /*Job newJob = Job(Employer aEmployer, Location aLocation,
                    PositionType aPositionType, CoreCompetency aSkill)
            newJob = JobData.add(newJob); */
            //OR
        /*    newJob = Job(@RequestParam name.value, @RequestParam employer.value, @RequestParam location.value, @RequestParam positionType.value, @RequestParam coreCompetency);
            then repeat model.addAttributes above   */


        // new Job and add it to the jobData data store. Then
        // redirect to the job detail view for the new Job.
            //First calls the constructor:  Job newJob = new Job();
            //Looks for getter  newJob.setName(Request.getParameter("name"); ???
}
