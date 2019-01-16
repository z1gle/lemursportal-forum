// One class for one link, it's not allowed to put more than one link in a class.


// The syntax below is an example of how to use it

/*<script type="text/javascript">
  $( document ).ready(function() {
    begin('http://localhost:8088/lemurs/preview', '.previewUrl');
  });
</script>*/

function begin(server, target, blank) {
  var allTarget = $(target);
  var totalIndex = 0;
  allTarget.each(function( index ) {
    $( this ).addClass('url-preview-multiple-' + index);
    procede($( this ).find('a')[0].href, server, '.url-preview-multiple-' + index, blank);
    totalIndex++;
  });
}

function procede(url, server, target, blank) {
  $.get(
  server,
  { link:url },
  function(data) {
    console.log(data);
    var template = '';
    var keys = getKeys(data);
    var ogImage = '';
    var title = '';
    var description = '';
    var icon = '';

    for (var i = keys.length - 1; i >= 0; i--) {
      if (keys[i] == 'ogTitle') {
        title = data.ogTitle;
      } else if (keys[i] == 'ogImg') {
        ogImage = data.ogImg;
      } else if (keys[i] == 'ogDescription') {
        description = data.ogDescription;
      } else if (keys[i] == 'description') {
        if (description == '') {
          description = data.description;
        }
      } else if (keys[i] == 'title') {
        if (title == '') {
          title = data.title;
        }
      } else if (keys[i] == 'icon') {
        icon = data.icon;
      }
    }

    if (ogImage == '' && description == '') {
      template = addIncompleteViewer(url, title, icon);
    } else {
      if (ogImage == '') {
        ogImage = icon;
      }
      template = addCompleteViewer(url, ogImage, title, description, icon, blank);
    }

    $(target).prepend(template);
    $(target).find('a')[0].remove();
    console.log('target : ' + target);
  },
  "json");
}

function addCompleteViewer(url, ogImage, ogTitle, description, icon, blank) {
  if (description.length > 200) {
    description = description.substring(0, 200) + '...';
  }
  var urlToShow = url;
  if (url.length > 50) {
    urlToShow = url.substring(0, 50) + '...';
  }
  var viewer = '';
    viewer += '<div class="preview-place" onclick="window.location.assign(\'' + url + '\')">';
    viewer += '    <div class="link-img">';
    viewer += '      <img src="'+ blank +'" style="background-image: url(' + ogImage + '); background-size: cover; background-position: 50% 48%;">';
    viewer += '    </div>';
    viewer += '    <div class="link-text">';
    viewer += '      <div class="link-description">';
    viewer += '        <h1>' + ogTitle + '</h1>';
    viewer += '        <span id="url-preview-description">';
    viewer +=             description;
    viewer += '        </span>';
    viewer += '      </div>';
    viewer += '       <div class="link-url">';
    viewer += '        <img src="' + icon + '">';
    viewer += '        <span>' + urlToShow + '</span>';
    viewer += '      </div>';
    viewer += '    </div>';
    viewer += '  </div>';
  return viewer;
}

function addIncompleteViewer(url, title, icon) {
  var viewer = '';
    viewer += '  <div class="p-p-incomplete" onclick="window.location.assign(\'' + url + '\')">';
    viewer += '    <div class="link-url-incomplete">';
    viewer += '      <img src="' + icon + '">';
    viewer += '      <div class="right-side">';
    viewer += '        <h1>' + title + '</h1>';
    viewer += '        <span>' + url + '</span>';
    viewer += '      </div>';
    viewer += '    </div>';
    viewer += '  </div>';
  return viewer;
}

function getKeys (data) {
  var keys = [];
  for(var k in data) keys.push(k);
  return keys;
}