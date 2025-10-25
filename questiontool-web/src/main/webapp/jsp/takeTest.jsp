<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.ntt.questiontool.model.Question" %>
<%@ page import="com.ntt.questiontool.model.Option" %>
<%
    // get attributes set by servlet
    String language = (String) request.getAttribute("language");
    if (language == null) {
        language = (String) request.getParameter("language");
    }
    List<Question> questions = (List<Question>) request.getAttribute("questions");
%>
<!doctype html>
<html>
<head>
    <meta charset="UTF-8">
    <title><%= (language != null ? language : "Programming") %> Test</title>
    <!-- link to CSS (use context path so it resolves correctly) -->
    <link rel="stylesheet" href="<%= request.getContextPath() %>/assets/css/style.css">
    <style>
      /* small fallback styling so it looks decent even if CSS missing */
      body{font-family: Arial, Helvetica, sans-serif; background:#f6f7fb; margin:0; padding:24px;}
      .card{max-width:900px;margin:0 auto;background:#fff;padding:28px;border-radius:6px;box-shadow:0 2px 6px rgba(0,0,0,.06)}
      h1{text-align:center;margin-top:0}
      .question-block{padding:18px 0}
      .question-title{font-weight:700;margin:0 0 8px}
      .question-body{color:#666;margin:0 0 12px}
      .option{margin:8px 0}
      .no-questions{color:#666;padding:24px;text-align:center}
      .submit-row{text-align:center;margin-top:20px}
      .debug{font-size:12px;color:#999;margin-top:12px}
    </style>

    <script>
      // Ensure every question has a selected radio before submitting
      function validateAndSubmit() {
        const qElems = document.querySelectorAll('[data-qid]');
        for (let i=0;i<qElems.length;i++){
          const qid = qElems[i].getAttribute('data-qid');
          const radios = document.getElementsByName('answer_' + qid);
          let any = false;
          for (let r=0; r<radios.length; r++){ if (radios[r].checked) { any = true; break; } }
          if (!any) {
            alert('Please answer question #' + (i+1));
            return false;
          }
        }
        return true;
      }
    </script>
</head>
<body>
  <div class="card">
    <h1><%= (language != null ? language : "Programming") %> Programming Test</h1>

    <form method="post" action="<%= request.getContextPath() %>/result" onsubmit="return validateAndSubmit();">
      <input type="hidden" name="language" value="<%= (language!=null?language:"") %>">

      <% if (questions == null || questions.isEmpty()) { %>
        <div class="no-questions">No questions found for <strong><%= (language!=null?language:"") %></strong>.</div>
      <% } else {
           int qNo = 1;
           for (Question q : questions) {
               List<Option> opts = q.getOptions();
               String qTitle = q.getTitle() != null ? q.getTitle() : (q.getQuestionText() != null ? q.getQuestionText() : "Question");
      %>

        <div class="question-block" data-qid="<%= q.getId() %>">
          <div class="question-title">Q<%= qNo++ %>: <%= qTitle %></div>
          <div class="question-body"><%= q.getBody() != null ? q.getBody() : "" %></div>

          <% if (opts == null || opts.isEmpty()) { %>
              <div class="option"><em>Option not available</em></div>
          <% } else {
               for (Option o : opts) {
          %>
            <div class="option">
              <label>
                <input type="radio" name="answer_<%= q.getId() %>" value="<%= o.getId() %>">
                <%= o.getOptionText() != null ? o.getOptionText() : "Option not available" %>
              </label>
            </div>
          <%   } // end for options
             } // end else options
          %>

          <hr>
        </div>

      <%   } // end for questions
         } // end else
      %>

      <div class="submit-row">
        <button type="submit">Submit Test</button>
      </div>

    </form>

    <div class="debug">
      <%-- Small debug output to help — remove in production --%>
      DEBUG: request.getAttribute('language') = <strong><%= language %></strong><br/>
      DEBUG: request.getAttribute('questions') class = <%= (questions==null ? "null" : questions.getClass().getName()) %><br/>
      DEBUG: questions.size() = <%= (questions==null ? 0 : questions.size()) %>
    </div>
  </div>
</body>
</html>
