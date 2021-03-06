package rugal.sample.controller;

import ml.rugal.sshcommon.springmvc.util.Message;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import rugal.sample.common.CommonMessageContent;
import rugal.sample.core.entity.Student;
import rugal.sample.core.repository.StudentRepository;

/**
 *
 * A sample controller class for GET/DELETE/POST/PUT.
 *
 * @author Rugal Bernstein
 */
@Controller
@RequestMapping(value = "/student")
public class StudentAction
{

    private static final Logger LOG = LoggerFactory.getLogger(StudentAction.class.getName());

    @Autowired
    private StudentRepository studentRepository;

    /**
     * Persist a student bean into database.
     *
     * @param bean student bean resembled from request body.
     *
     * @return The persisted student bean.
     */
    @ResponseBody
    @RequestMapping(method = RequestMethod.POST)
    public Message registerStudent(@RequestBody Student bean)
    {
        studentRepository.save(bean);
        /*
         * Now we need to push message notify them
         */
        return Message.successMessage(CommonMessageContent.SAVE_SUCCEED, bean);
    }

    /**
     * Update a student bean.
     *
     * @param id   primary key of target student.
     * @param bean the newer student bean
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Message updateStudentProfile(@PathVariable("id") String id, @RequestBody Student bean)
    {
        Student dbStudent = studentRepository.findOne(new ObjectId(id));
        if (null != dbStudent)
        {
            bean.setId(id);
            studentRepository.save(bean);
        }
        /*
         * Here we need to push message to client
         */
        return Message.successMessage(CommonMessageContent.PROFILE_UPDATED, bean);
    }

    /**
     * DELETE a student record from database.
     *
     * @param id the target student id.
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Message deregister(@PathVariable("id") String id)
    {
        Student bean = studentRepository.findOne(new ObjectId(id));
        if (null != bean)
        {
            studentRepository.delete(new ObjectId(id));
        }
        return Message.successMessage(CommonMessageContent.STUDENT_DELETED, bean);
    }

    /**
     * GET a student record from database.
     *
     * @param id primary key of target student.
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Message retrieve(@PathVariable("id") String id)
    {
        Student bean = studentRepository.findOne(new ObjectId(id));
        return Message.successMessage(CommonMessageContent.GET_STUDENT, bean);
    }
}
